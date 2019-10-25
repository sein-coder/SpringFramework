package com.kh.spring.common.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.springframework.stereotype.Component;

@Component
public class RSAEncrypto implements MyEncrypt {
	//key값을 변수로 보관
	//key가 두개가 존재함
	private PublicKey publickey;
	private PrivateKey privatekey;
	
	public RSAEncrypto() throws Exception {
		String path = this.getClass().getResource("/").getPath();
		path = path.substring(0,path.lastIndexOf("/target"));
		File f = new File(path+"/"+"src/main/webapp/WEB-INF/keys.bs");
		if(f.exists()) {
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){
				Map<String,Object> keys = (Map)ois.readObject();
				publickey = (PublicKey)keys.get("public");
				privatekey = (PrivateKey)keys.get("private");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			if(publickey==null||privatekey==null) {
				getKey();
			}
		}
	}
	
	private void getKey() throws NoSuchAlgorithmException {
		SecureRandom ser = new SecureRandom();
		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
		keygen.initialize(2048,ser);
		KeyPair keypair = keygen.generateKeyPair();
		publickey = keypair.getPublic(); //공개키 생성
		privatekey = keypair.getPrivate(); //개인키, 비밀키 생성
		/////암호화키 생성 끝.
		String path = this.getClass().getResource("/").getPath();
		path = path.substring(0,path.lastIndexOf("/target"));
		File f = new File(path+"/"+"src/main/webapp/WEB-INF/keys.bs");
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))){
			Map<String, Object> keys = new HashMap<String,Object>();
			keys.put("public",publickey);
			keys.put("private",privatekey);
			oos.writeObject(keys);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String encrypt(String msg) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publickey);
		byte[] encrypt = cipher.doFinal(msg.getBytes());
		return Base64.getEncoder().encodeToString(encrypt);
	}

	@Override
	public String decrypt(String msg) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privatekey);
		byte[] enc = Base64.getDecoder().decode(msg);
		byte[] decrypt = cipher.doFinal(enc);
		return new String(decrypt,"UTF-8");
	}
	
	
	
}
