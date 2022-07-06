package work.ctstudy.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import work.ctstudy.entity.User;
import work.ctstudy.mapper.UserMapper;
import work.ctstudy.shiro.MyByteSource;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String num = (String) principalCollection.getPrimaryPrincipal();
        User user = userMapper.selectUserByNum(num);
        String role = user.getRole();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(role);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String num = (String) token.getPrincipal();
        User user = userMapper.selectUserByNum(num);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(
                    num,
                    user.getPassword(), //密码会自动去比较
                    new MyByteSource(user.getSalt()),
                    this.getName()
            );
        }

        return null; //没有该用户
    }
}
