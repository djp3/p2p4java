package edu.uci.ics.luci.p2p4java.impl.membership.pse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.security.PrivateKey;
import java.util.Arrays;

import javax.crypto.EncryptedPrivateKeyInfo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.uci.ics.luci.p2p4java.impl.membership.pse.PSEUtils.IssuerInfo;

public class PSEUtilsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testPKCS5() {
		try {
			IssuerInfo test = PSEUtils.genCert("test", null);

			EncryptedPrivateKeyInfo encPrivKey = PSEUtils
					.pkcs5_Encrypt_pbePrivateKey("password".toCharArray(),
							test.subjectPkey, 500);

			assertNotNull("Could not encrypt Private Key", encPrivKey);

			PrivateKey decPrivKey = PSEUtils.pkcs5_Decrypt_pbePrivateKey(
					"password".toCharArray(), test.subjectPkey.getAlgorithm(),
					encPrivKey);

			assertNotNull("Could not decrypt Private Key", decPrivKey);

			Arrays.equals(test.subjectPkey.getEncoded(),
					decPrivKey.getEncoded());

			byte[] encPrivKeyDer = encPrivKey.getEncoded();

			EncryptedPrivateKeyInfo deserialedencPrivKey = new EncryptedPrivateKeyInfo(
					encPrivKeyDer);

			decPrivKey = PSEUtils.pkcs5_Decrypt_pbePrivateKey(
					"password".toCharArray(), test.subjectPkey.getAlgorithm(),
					deserialedencPrivKey);

			assertNotNull("Could not decrypt Private Key", decPrivKey);

			Arrays.equals(test.subjectPkey.getEncoded(),
					decPrivKey.getEncoded());
		} catch (Exception caught) {
			caught.printStackTrace();
			fail("exception thrown : " + caught.getMessage());
		}
	}
}