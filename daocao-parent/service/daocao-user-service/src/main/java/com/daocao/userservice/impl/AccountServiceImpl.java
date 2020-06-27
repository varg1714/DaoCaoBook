package com.daocao.userservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.daocao.dao.AccountMapper;
import com.daocao.entity.Account;
import com.daocao.entity.AccountExample;
import com.daocao.myentity.PageResult;
import com.daocao.user.service.AccountService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    RedisTemplate<String,String> redisTemplate;

    @Value("${signName}")
    String signName;

    @Value("${templateCode}")
    String templateCode;

    /**
     * 查询全部
     */
    @Override
    public List<Account> findAll() {
        return accountMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Account> page = (Page<Account>) accountMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(Account account) {

    	initAccount(account);
        accountMapper.insert(account);
        // 删除redis中保存的短信验证码
        redisTemplate.boundHashOps("telCheck").delete(account.getTel());
    }

    /**
     * 初始账号默认信息
     *
     * @param account 新创建账户
     */
    public void initAccount(Account account) {

    	Date date = new Date();
    	account.setCreatedate(date);
    	account.setUpdatedate(date);

		account.setStatus("0");
		account.setBalance(new BigDecimal(0));
		account.setTelchecked("1");
		account.setEmailchecked("0");
		account.setUserlevel("0");
		account.setGoodreputationnum(0);
		account.setBadreputationnum(0);
    }

    /**
     * 修改
     */
    @Override
    public void update(Account account) {
        accountMapper.updateByPrimaryKey(account);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public Account findOne(String id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            accountMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult findPage(Account account, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();

        if (account != null) {
            if (account.getUsername() != null && account.getUsername().length() > 0) {
                criteria.andUsernameEqualTo(account.getUsername());
            }
            if (account.getPassword() != null && account.getPassword().length() > 0) {
                criteria.andPasswordLike("%" + account.getPassword() + "%");
            }
            if (account.getEmail() != null && account.getEmail().length() > 0) {
                criteria.andEmailLike("%" + account.getEmail() + "%");
            }
            if (account.getTel() != null && account.getTel().length() > 0) {
                criteria.andTelLike("%" + account.getTel() + "%");
            }
            if (account.getNickname() != null && account.getNickname().length() > 0) {
                criteria.andNicknameLike("%" + account.getNickname() + "%");
            }
            if (account.getName() != null && account.getName().length() > 0) {
                criteria.andNameLike("%" + account.getName() + "%");
            }
            if (account.getSex() != null && account.getSex().length() > 0) {
                criteria.andSexLike("%" + account.getSex() + "%");
            }
            if (account.getStatus() != null && account.getStatus().length() > 0) {
                criteria.andStatusLike("%" + account.getStatus() + "%");
            }
            if (account.getHeadpic() != null && account.getHeadpic().length() > 0) {
                criteria.andHeadpicLike("%" + account.getHeadpic() + "%");
            }
            if (account.getTelchecked() != null && account.getTelchecked().length() > 0) {
                criteria.andTelcheckedLike("%" + account.getTelchecked() + "%");
            }
            if (account.getEmailchecked() != null && account.getEmailchecked().length() > 0) {
                criteria.andEmailcheckedLike("%" + account.getEmailchecked() + "%");
            }
            if (account.getUserlevel() != null && account.getUserlevel().length() > 0) {
                criteria.andUserlevelLike("%" + account.getUserlevel() + "%");
            }

        }

        Page<Account> page = (Page<Account>) accountMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public int updateBaseInfo(Account account)  {
        account.setUpdatedate(new Date());
        return accountMapper.updateBaseInfo(account);

    }

    @Override
    public boolean sendTelCode(String tel) {
        // 生成验证码
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 6; i++){
            code.append(random.nextInt(10));
        }

        // 封装短信
        Map<String,String> map = new HashMap<>();
        map.put("PhoneNumbers", tel);
        map.put("SignName", signName);
        map.put("TemplateCode", templateCode);
        map.put("TemplateParam", "{\"code\":\"" + code + "\"}");

        // 发送到消息队列
        rabbitTemplate.convertAndSend("smsTelCheckExchange","topic.smsCheck",map);

        // 保存到redis
        redisTemplate.boundHashOps("telCheck").put(tel,code.toString());
        return true;
    }

    @Override
    public boolean checkTelCode(String tel, String code) {

        String telCheck = (String)redisTemplate.boundHashOps("telCheck").get(tel);
        if(telCheck != null){
            return telCheck.equals(code);
        }
        return false;
    }

    @Override
    public int resetPassword(Account account, String checkCode) {

        // 检查验证码
        if(!checkTelCode(account.getTel(),checkCode)){
            return -1;
        }

        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(account.getUsername());
        int i = accountMapper.updateByExampleSelective(account, example);

        // 更改成功后删除验证码信息
        redisTemplate.boundHashOps("telCheck").delete(account.getTel());
        return i;
    }

}
