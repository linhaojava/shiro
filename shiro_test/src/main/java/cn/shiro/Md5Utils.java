package cn.shiro;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * MD5
 * 
 * @className:Md5UtilsGloble
 * @description:
 * @company:美讯在线
 * @author：lh
 * @createDate：2018年2月23日上午11:14:43
 * @version:1.0
 */
public class Md5Utils {

	public static void main(String[] args) {
		// md5加密，不加盐
		String password_md5 = new Md5Hash("111111").toString();
		System.out.println("md5加密，不加盐=" + password_md5);

		// md5加密，加盐，一次散列
		String password_md5_sale_1 = new Md5Hash("111111", "eteokues", 1).toString();
		System.out.println("password_md5_sale_1=" + password_md5_sale_1);
		String password_md5_sale_2 = new Md5Hash("111111", "uiwueylm", 1).toString();
		System.out.println("password_md5_sale_2=" + password_md5_sale_2);
		// 两次散列相当于md5(md5())

		// 使用SimpleHash
		String simpleHash = new SimpleHash("MD5", "111111", "eteokues", 1).toString();
		System.out.println(simpleHash);
	}
}
