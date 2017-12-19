package com.hr.platform.encrypt;

public class PlatFormEncrypt {
	public static  String AESKey1  = RandomString.getRandomString16();
	public static final String AESKey2 = RandomString.getRandomString16();
	public static final String SPLIT=",";
	
	
	public static String encodeData(String sSrc) throws Exception {
		String paramAes = AES.Encrypt(sSrc, AESKey1, AESKey2);
		String paramSha256=SHA256.encrypt(sSrc);
		String data=paramSha256+SPLIT+AESKey1+SPLIT+AESKey2;
//		byte[] paramRSA=RSAUtil.encryptByPublicKey(RSAUtil.RSApubKey, data.getBytes());
		byte[] paramRSA=RSAEncrypt.encrypt(RSAEncrypt.publicKey, data.getBytes());
		String paramRsa=new String(paramRSA);
		
		return paramAes+SPLIT+paramRsa;
	}
	
	public static String decode(String sSrc) throws Exception {

		String[] data = sSrc.split(PlatFormEncrypt.SPLIT);
		String paramAes = data[0];
		String paramRsa = data[1];
	
//		String paramData = new String(RSAUtil.decryptByPrivateKey(RSAUtil.RSApriKey, paramRsa.getBytes()));
		String paramData=new String(RSAEncrypt.decrypt(RSAEncrypt.privateKey, paramRsa.getBytes()));
		String sha256Data[] = paramData.split(PlatFormEncrypt.SPLIT);
		String paramSha256 = Base64Util.decode(sha256Data[0]);
//		String paramSha256=sha256Data[0];
		String aesKey1 = sha256Data[1];
		String aesKey2 = sha256Data[2];

		String param = AES.Decrypt(paramAes, aesKey1,
				aesKey2);
		
		String paramReSha256 = Base64Util.decode(SHA256.encrypt(param));
//		String paramReSha256 = SHA256.encrypt(param);

		if (paramSha256.equals(paramReSha256)) {
			return param;
		}
		return null;

	}
}
