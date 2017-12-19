package com.hr.platform.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.hr.platform.application.HRApplication;
import com.hr.platform.util.Util;
import com.lidroid.xutils.util.IOUtils;

public class GetKeFromFile {

	private static KeyPairGenerator keyPairGen = null;
	private static KeyFactory keyFactory = null;
	/**
	 * 保存生成的密钥对的文件名称。
	 */
	private static final String RSA_PAIR_FILENAME = "RSA_PAIR.txt";
	/**
	 * 保存生成的密钥对的公钥的文件名称。
	 */
	private static final String RSA_PAIR_PUBLIC_FILENAME = "RSA_PUBLIC.txt";
	/**
	 * 算法名称
	 */
	private static final String ALGORITHOM = "RSA";
	/**
	 * 密钥大小
	 */
	private static final int KEY_SIZE = 1024;
	/**
	 * 默认的安全服务提供者
	 */
	private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();
	/**
	 * 缓存的密钥对。
	 */
	private static KeyPair oneKeyPair = null;

	private static File rsaPairFile = null;

	// private static File rsaPublicFile = null;

	static {
		try {
			keyPairGen = KeyPairGenerator.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
			keyFactory = KeyFactory.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		rsaPairFile = new File(getRSAPairFilePath());
		// rsaPublicFile = new File(getRSAPublicFilePath());

	}

	/**
	 * 返回生成/读取的密钥对文件的路径。
	 */
	private static String getRSAPairFilePath() {
		String path = "d:";
		return (path + "/RSA/" + RSA_PAIR_FILENAME);
	}

	/**
	 * 返回生成/读取的密钥对的公钥文件的路径。
	 */
	// private static String getRSAPublicFilePath() {
	// String path = "";
	// return (path + "/RSA/" + RSA_PAIR_PUBLIC_FILENAME);
	// }

	// 同步读出保存的密钥对
	private static KeyPair readKeyPair() {
		InputStream fis = null;
		ObjectInputStream ois = null;
		try {
			// fis = FileUtils.openInputStream(rsaPairFile);
			fis = HRApplication.getInstance().getApplicationContext().getAssets().open("RSA_PAIR.txt");
			ois = new ObjectInputStream(fis);
			oneKeyPair = (KeyPair) ois.readObject();
			return oneKeyPair;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			IOUtils.closeQuietly(ois);
			IOUtils.closeQuietly(fis);
		}
		return null;
	}

	// 同步读出保存的密钥对
	// private static RSAPublicKey readKeyPublic() {
	// FileInputStream fis = null;
	// ObjectInputStream ois = null;
	// try {
	// fis = FileUtils.openInputStream(rsaPublicFile);
	// ois = new ObjectInputStream(fis);
	// RSAPublicKey publicKey = (RSAPublicKey) ois.readObject();
	// return publicKey;
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// } finally {
	// IOUtils.closeQuietly(ois);
	// IOUtils.closeQuietly(fis);
	// }
	// return null;
	// }
	/**
	 * 若需要创建新的密钥对文件，则返回 {@code true}，否则 {@code false}。
	 */
	private static boolean isCreateKeyPairFile() {
		// 是否创建新的密钥对文件
		boolean createNewKeyPair = false;
		// if (!rsaPairFile.exists() || rsaPairFile.isDirectory()) {
		// createNewKeyPair = true;
		// }
		InputStream fis = null;
		try {
			fis = HRApplication.getInstance().getApplicationContext().getAssets().open("RSA_PAIR.txt");
			createNewKeyPair = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			createNewKeyPair = true;
		} finally {
			Util.safeClose(fis);
		}

		return createNewKeyPair;
	}

	/**
	 * 返回RSA密钥对。
	 */
	public static KeyPair getKeyPair() {
		// 首先判断是否需要重新生成新的密钥对文件
		if (isCreateKeyPairFile()) {
			// 直接强制生成密钥对文件，并存入缓存。
			return generateKeyPair();
		}
		if (oneKeyPair != null) {
			return oneKeyPair;
		}
		return readKeyPair();
	}

	/**
	 * 生成并返回RSA密钥对。
	 */
	private static synchronized KeyPair generateKeyPair() {
		try {
			keyPairGen.initialize(KEY_SIZE,
					new SecureRandom(DateFormatUtils.format(new Date(), "yyyyMMdd").getBytes()));
			oneKeyPair = keyPairGen.generateKeyPair();
			saveKeyPair(oneKeyPair);

			return oneKeyPair;
		} catch (InvalidParameterException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 将指定的RSA密钥对以文件形式保存。
	 *
	 * @param keyPair
	 *            要保存的密钥对。
	 */
	private static void saveKeyPair(KeyPair keyPair) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		FileOutputStream fosPublic = null;
		ObjectOutputStream oosPublic = null;
		try {

			fos = FileUtils.openOutputStream(rsaPairFile);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(keyPair);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			IOUtils.closeQuietly(oos);
			IOUtils.closeQuietly(fos);
		}
		// 保存公钥
		// try {
		// RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		// // String[] attr = new String[2];
		// // attr[0] = new
		// //
		// String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray()));
		// // attr[1] = new
		// // String(Hex.encodeHex(publicKey.getModulus().toByteArray()));
		// fosPublic = FileUtils.openOutputStream(rsaPublicFile);
		// oosPublic = new ObjectOutputStream(fosPublic);
		// oosPublic.writeObject(publicKey);
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// } finally {
		// IOUtils.closeQuietly(oosPublic);
		// IOUtils.closeQuietly(fosPublic);
		// }
	}
}
