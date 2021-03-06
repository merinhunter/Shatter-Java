package cipher;

import java.security.SecureRandom;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESLibrary {
	private final String ALGORITHM = "AES/CBC/PKCS5Padding";
	public final static int KEY_SIZE = 16;

	private SecureRandom random;

	public AESLibrary() {
		random = new SecureRandom();
	}

	public SecretKeySpec generateSymmetricKey() {
		byte[] keyBytes = new byte[KEY_SIZE];
		random.nextBytes(keyBytes);
		return new SecretKeySpec(keyBytes, ALGORITHM);
	}

	public SecretKeySpec generateSymmetricKey(byte[] keyBytes) {
		return new SecretKeySpec(keyBytes, ALGORITHM);
	}

	public IvParameterSpec generateIV() {
		byte[] ivBytes = new byte[KEY_SIZE];
		random.nextBytes(ivBytes);
		return new IvParameterSpec(ivBytes);
	}

	public IvParameterSpec generateIV(byte[] ivBytes) {
		return new IvParameterSpec(ivBytes);
	}
}
