package jwt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import io.jsonwebtoken.Claims;

public class JwtDemo {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	@Test
	public void test1() {// 生成JWT
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("username", "zss");
		claims.put("age", 18);
		claims.put("role", "VIP10");

		String jwt = JwtUtils.createJwt(claims, 60*1000);
		System.out.println(jwt);

		Claims parseJwt = JwtUtils.parseJwt(jwt);
		for (Map.Entry<String, Object> entry : parseJwt.entrySet()) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		Date d1 = parseJwt.getIssuedAt();
		Date d2 = parseJwt.getExpiration();
		System.out.println("令牌签发时间：" + sdf.format(d1));
		System.out.println("令牌过期时间：" + sdf.format(d2));
	}

	@Test
	public void test2() {// 解析oldJwt
		String oldJwt = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVklQMTAiLCJleHAiOjE1NjkxOTU4OTgsImlhdCI6MTU2OTE5NTgzOCwiYWdlIjoxOCwianRpIjoiNzc1NTJmMzc4OWNiNDM5ZjljODJhZDhmYzIxNTkwMDUiLCJ1c2VybmFtZSI6InpzcyJ9.ng1irEyl89ulQtMftSCBCW7VigcxhB1a62ms9DsgBHU";
		Claims parseJwt = JwtUtils.parseJwt(oldJwt);
		for (Map.Entry<String, Object> entry : parseJwt.entrySet()) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		Date d1 = parseJwt.getIssuedAt();
		Date d2 = parseJwt.getExpiration();
		System.out.println("令牌签发时间：" + sdf.format(d1));
		System.out.println("令牌过期时间：" + sdf.format(d2));
	}

	@Test
	public void test3() {// 复制jwt，并延时30秒
		String oldJwt = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVklQMTAiLCJleHAiOjE1NjkxOTU4OTgsImlhdCI6MTU2OTE5NTgzOCwiYWdlIjoxOCwianRpIjoiNzc1NTJmMzc4OWNiNDM5ZjljODJhZDhmYzIxNTkwMDUiLCJ1c2VybmFtZSI6InpzcyJ9.ng1irEyl89ulQtMftSCBCW7VigcxhB1a62ms9DsgBHU";
		String jwt = JwtUtils.copyJwt(oldJwt, JwtUtils.JWT_WEB_TTL);
		Claims parseJwt = JwtUtils.parseJwt(jwt);
		for (Map.Entry<String, Object> entry : parseJwt.entrySet()) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		Date d1 = parseJwt.getIssuedAt();
		Date d2 = parseJwt.getExpiration();
		System.out.println("令牌签发时间：" + sdf.format(d1));
		System.out.println("令牌过期时间：" + sdf.format(d2));
	}

	@Test
	public void test4() {// 测试JWT的有效时间
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("username", "zss");
		String jwt = JwtUtils.createJwt(claims, 3 * 1000L);
		System.out.println(jwt);
		Claims parseJwt = JwtUtils.parseJwt(jwt);
		Date d1 = parseJwt.getIssuedAt();
		Date d2 = parseJwt.getExpiration();
		System.out.println("令牌签发时间：" + sdf.format(d1));
		System.out.println("令牌过期时间：" + sdf.format(d2));
	}

	@Test
	public void test5() {// 三秒后再解析上面过期时间只有三秒的令牌，因为过期则会报错io.jsonwebtoken.ExpiredJwtException
		String oldJwt = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVklQMTAiLCJleHAiOjE1NjkxOTU4OTgsImlhdCI6MTU2OTE5NTgzOCwiYWdlIjoxOCwianRpIjoiNzc1NTJmMzc4OWNiNDM5ZjljODJhZDhmYzIxNTkwMDUiLCJ1c2VybmFtZSI6InpzcyJ9.ng1irEyl89ulQtMftSCBCW7VigcxhB1a62ms9DsgBHU";
		Claims parseJwt = JwtUtils.parseJwt(oldJwt);
		// 过期后解析就报错了，下面代码根本不会执行
		Date d1 = parseJwt.getIssuedAt();
		Date d2 = parseJwt.getExpiration();
		System.out.println("令牌签发时间：" + sdf.format(d1));
		System.out.println("令牌过期时间：" + sdf.format(d2));
	}
}
