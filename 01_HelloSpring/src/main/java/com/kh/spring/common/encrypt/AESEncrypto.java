package com.kh.spring.common.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AESEncrypto implements MyEncrypt {

	//AES암호화방식으로 하기 위해서는 KEY가 있어야함
	//키 암호화, 복호화 할 수 있는 키
	//객체만들기
	//javax.crypto패키지, java.security패키지(키객체가 만들어져 있음)에서 제공
   
	private SecretKey key;//생성할 키를 저장하는 객체, 암호화&복호화할 수 있는 키
	//키가 새로 생길 가능성이 있어서  영원히 키를 보관할 수 있는 방법이 필요함
	//키값을 하나의 파일로 저장시키는 방법: WEB-INF/폴더에 키를 저장할 것
   
   
	//키를 구동시키기 위해 분기처리 
	public AESEncrypto() {
		String saveDir =this.getClass().getResource("/").getPath();
		saveDir =saveDir.substring(0,saveDir.lastIndexOf("/target"));// target/src/ web-inf가 있음
		File f=new File(saveDir+"/src/main/webapp/WEB-INF/securitykey.jy");
		if(f.exists()) {
			//저장된 파일에서 secretkey를 받아오기
			try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(f))) {
				key=(SecretKey)ois.readObject();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			if(key==null) {
				try {
					getKey();
				}catch(NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		}
	}
   
	//키를 만들기 위해 default생성자 생성하자
	private void getKey() throws NoSuchAlgorithmException{
		//key를 생성하는 메소드
		//1.특정한 랜덤값이 필요함, 정교해야함
		//2.Math.random() x:불안정함  ->SecureRandom o
		SecureRandom ser=new SecureRandom();
	  
		//key를 생성하는 객체를 추가
		//KeyGenerator ->key를 생성하는 객체(대칭키)
		KeyGenerator keygen;
		keygen=KeyGenerator.getInstance("AES");
		  
		keygen.init(128,ser);
		  
		//key생성해주자
		key=keygen.generateKey();//대칭키 생성완료! , 키를 통해 암호화,복호화 할 수 있어
	  
		//key를 영원히 보관하기 위해 파일로 저장하겠음
		//저장위치: ~~/WEB-INF/securitykey.jy
		String saveDir =this.getClass().getResource("/").getPath();
		//this.getClass().getResource("/").getPath();클래스의 경로, target/com.kh/common아래에 들어감
		//수정필요
		saveDir =saveDir.substring(0,saveDir.lastIndexOf("/target"));// target/src/ web-inf가 있음
		// 불러올 수 있는 위치에 파일을 저장하면 됨.
		File f=new File(saveDir+"/src/main/webapp/WEB-INF/securitykey.jy");
	  
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f))){
			oos.writeObject(key);//securitykey.jy파일의 key객체가 저장됨
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
   
   
	@Override
	public String encrypt(String msg) throws Exception {
		//암호화메소드
		//Cipher객체를 이용해서 암호화를 진행
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);//첫번째:정해진 상수 입력 //두번째:키를 적어주면 된다.
		byte[] enc = cipher.doFinal(msg.getBytes());
		
		return Base64.getEncoder().encodeToString(enc);
	}
	
	@Override
	public String decrypt(String msg) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);//첫번째:정해진 상수 입력 //두번째:키를 적어주면 된다.
		byte[] enc = Base64.getDecoder().decode(msg.getBytes());
		byte[] encResult = cipher.doFinal(enc);
		
		return new String(encResult,"UTF-8");
	}

}