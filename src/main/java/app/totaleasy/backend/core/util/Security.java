package app.totaleasy.backend.core.util;

import java.io.IOException;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class Security {

    private static final String ENCRYPTION_ALGORITHM;

    private static final String HASHING_FUNCTION;

    private static final BouncyCastleProvider PROVIDER;

    static {
        ENCRYPTION_ALGORITHM = "Ed25519";
        HASHING_FUNCTION = "SHA-512";

        PROVIDER = new BouncyCastleProvider();
        java.security.Security.addProvider(new BouncyCastleProvider());
    }

    private Security() {

    }

    public static boolean verifySignature(String content, String signature, byte[] publicKey) throws
        InvalidKeyException,
        SignatureException,
        IOException,
        NoSuchAlgorithmException,
        InvalidKeySpecException
    {
        SubjectPublicKeyInfo keyInfo = new SubjectPublicKeyInfo(
            new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519),
            publicKey
        );

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyInfo.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPTION_ALGORITHM, PROVIDER);
        PublicKey key = keyFactory.generatePublic(keySpec);

        Signature sig = Signature.getInstance(ENCRYPTION_ALGORITHM, PROVIDER);

        sig.initVerify(key);
        sig.update(Hex.decode(content));

        return sig.verify(Hex.decode(signature));
    }

    public static String sha512(String content) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(HASHING_FUNCTION, PROVIDER);

        digest.reset();
        digest.update(content.getBytes());

        return Hex.toHexString(digest.digest()).toUpperCase();
    }
}
