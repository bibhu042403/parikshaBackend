package com.example.pariksha.utlis;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
//import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class CryptoUtil {
    public static final String MVSL_STAMP_FILE_NAME = "/mvsl-stamp.enc";
    static CryptoUtil theCryptoUtil = null;
    public static final String SECRET_KEY = "rO0ABXNyAB9qYXZheC5jcnlwdG8uc3BlYy5TZWNyZXRLZXlTcGVjW0cLZuIwYU0CAAJMAAlhbGdvcml0aG10ABJMamF2YS9sYW5nL1N0cmluZztbAANrZXl0AAJbQnhwdAADREVTdXIAAltCrPMX+AYIVOACAAB4cAAAAAiY+HNUrua2oQ==";
    Key secretKey;
    Key aesKey;
    byte[] aesKeyData;
    PublicKey publicKey;
    PrivateKey privateKey;
    static String PUBLIC_KEY = "rO0ABXNyABRqYXZhLm1hdGguQmlnSW50ZWdlcoz8nx+pO/sdAwAGSQAIYml0Q291bnRJAAliaXRMZW5ndGhJABNmaXJzdE5vbnplcm9CeXRlTnVtSQAMbG93ZXN0U2V0Qml0SQAGc2lnbnVtWwAJbWFnbml0dWRldAACW0J4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHD///////////////7////+AAAAAXVyAAJbQqzzF/gGCFTgAgAAeHAAAAEAvwyOcqIiTF5Gn9295L1RFIyAlSFmacq53qPw/5JUCsmY1J7UMOdJjymbQlUKZhycMIak4aeDDbqg+67fImIY+VxnhD1rqxQFv71kbEdlP0GPBguHcSHh3+OWX+i3REBmaOInAejwFMe1a1DQseB1NFkirWBoBKg7BZZka1ZzJPIiVNCBSWfyJKHvt9pjAFOLU0dnffkprXk3HHjojqSJ39jw1O66AlogVhZJ2Nsvjl6nCHnyoW7l5067/Pjzv0o1wdikkzCqbMOSB7/sv8+Gse+NT+pCabbo7vBkG8fX2l1/44XRwt59sC5W26c5qzqx9gtKqM3wHar9YziPBCaj4XhzcQB+AAD///////////////7////+AAAAAXVxAH4ABAAAAAMBAAF4";
    static String PRIVATE_KEY = "rO0ABXNyABRqYXZhLm1hdGguQmlnSW50ZWdlcoz8nx+pO/sdAwAGSQAIYml0Q291bnRJAAliaXRMZW5ndGhJABNmaXJzdE5vbnplcm9CeXRlTnVtSQAMbG93ZXN0U2V0Qml0SQAGc2lnbnVtWwAJbWFnbml0dWRldAACW0J4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHD///////////////7////+AAAAAXVyAAJbQqzzF/gGCFTgAgAAeHAAAAEAvwyOcqIiTF5Gn9295L1RFIyAlSFmacq53qPw/5JUCsmY1J7UMOdJjymbQlUKZhycMIak4aeDDbqg+67fImIY+VxnhD1rqxQFv71kbEdlP0GPBguHcSHh3+OWX+i3REBmaOInAejwFMe1a1DQseB1NFkirWBoBKg7BZZka1ZzJPIiVNCBSWfyJKHvt9pjAFOLU0dnffkprXk3HHjojqSJ39jw1O66AlogVhZJ2Nsvjl6nCHnyoW7l5067/Pjzv0o1wdikkzCqbMOSB7/sv8+Gse+NT+pCabbo7vBkG8fX2l1/44XRwt59sC5W26c5qzqx9gtKqM3wHar9YziPBCaj4XhzcQB+AAD///////////////7////+AAAAAXVxAH4ABAAAAQBfMxGUe8hdh6jnpqYmYlQRykWUinUvCE8Aa27+21q3mWdno9GWoNnd1dzA/AFXirAl2iI1M7SxVas8XxJfXsSVFX+bvMN3F14aAnUVI6qHD9MGLx6JuFmGqDDZj+wqvXCKmQ7Y+8sOf8meNUIZMQEWWchW3rBWicIHcb6OYiC0ans3YtrSPM0eBeR4QzQFQofzLHLq+zb4eC/icnC2HC2QfZ+UQjMGKEl/t0xLQxsVeIXu4Sa1BZvxwuKTKekw5yJBC2yUnH6PcY+nj2RCbGLJ1oN0yjUqfMDRaVMqqCinyzZFwb4d2vhgUxjQrwCSR3ZkYZ+Dv3fWDPwdWpj/0IPVeA==";
    public static MessageDigest messageDigest;

    public static synchronized CryptoUtil getInstance() {
        if (theCryptoUtil == null) {
            theCryptoUtil = new CryptoUtil();
        }

        return theCryptoUtil;
    }

    private CryptoUtil() {
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode("rO0ABXNyAB9qYXZheC5jcnlwdG8uc3BlYy5TZWNyZXRLZXlTcGVjW0cLZuIwYU0CAAJMAAlhbGdvcml0aG10ABJMamF2YS9sYW5nL1N0cmluZztbAANrZXl0AAJbQnhwdAADREVTdXIAAltCrPMX+AYIVOACAAB4cAAAAAiY+HNUrua2oQ==")));
            this.secretKey = (Key)inputStream.readObject();
            inputStream.close();
            this.publicKey = getPublicKey();
            this.privateKey = getPrivateKey();
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            SecretKey key = kgen.generateKey();
            this.aesKeyData = key.getEncoded();
            this.aesKey = new SecretKeySpec(this.aesKeyData, "AES");
        } catch (Exception var4) {
        }

    }

    public static PublicKey getPublicKey() {
        ObjectInputStream oin = null;

        PublicKey var7;
        try {
            oin = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(Base64.decode(PUBLIC_KEY))));
            Key key = (Key)oin;
            BigInteger m = (BigInteger)oin.readObject();
            BigInteger e = (BigInteger)oin.readObject();
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PublicKey pubKey = fact.generatePublic(keySpec);
            var7 = pubKey;
        } catch (Exception var16) {
            throw new RuntimeException("unable to initialize the public key", var16);
        } finally {
            if (oin != null) {
                try {
                    oin.close();
                } catch (IOException var15) {
                }
            }

        }

        return var7;
    }

    public static PrivateKey getPrivateKey() {
        ObjectInputStream oin = null;

        PrivateKey var6;
        try {
            oin = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(Base64.decode(PRIVATE_KEY))));
            BigInteger m = (BigInteger)oin.readObject();
            BigInteger e = (BigInteger)oin.readObject();
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PrivateKey priKey = fact.generatePrivate(keySpec);
            var6 = priKey;
        } catch (Exception var15) {
            throw new RuntimeException("unable to initialize the private key", var15);
        } finally {
            if (oin != null) {
                try {
                    oin.close();
                } catch (IOException var14) {
                }
            }

        }

        return var6;
    }

    public byte[] decrypt(byte[] text) {
        ByteArrayOutputStream outputWriter = null;
        CipherInputStream inputReader = null;

        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, this.getSecretKey());
            outputWriter = new ByteArrayOutputStream();
            inputReader = new CipherInputStream(new ByteArrayInputStream(text), cipher);
            this.copy(inputReader, outputWriter);
            outputWriter.flush();
        } catch (Exception var13) {
        } finally {
            try {
                if (outputWriter != null) {
                    outputWriter.close();
                }

                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception var12) {
            }

        }

        return outputWriter != null ? outputWriter.toByteArray() : null;
    }

    public byte[] encrypt(byte[] text) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CipherOutputStream outputWriter = null;
        ByteArrayInputStream inputReader = null;

        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, this.getSecretKey());
            outputWriter = new CipherOutputStream(baos, cipher);
            inputReader = new ByteArrayInputStream(text);
            this.copy(inputReader, outputWriter);
            outputWriter.flush();
        } catch (Exception var14) {
        } finally {
            try {
                if (outputWriter != null) {
                    outputWriter.close();
                }

                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception var13) {
            }

        }

        return outputWriter != null ? baos.toByteArray() : null;
    }

    public void encryptFile(String srcFileName, String destFileName) throws IOException {
        if (destFileName == null) {
            int i = srcFileName.lastIndexOf("\\");
            if (i == -1) {
                destFileName = "crypt\\" + srcFileName;
                (new File("crypt")).mkdirs();
            } else {
                destFileName = srcFileName.substring(0, i + 1) + "crypt\\" + srcFileName.substring(i + 1);
                (new File(srcFileName.substring(0, i + 1) + "crypt")).mkdirs();
            }
        }

        CipherOutputStream outputWriter = null;
        InputStream inputReader = null;

        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, this.getSecretKey());
            outputWriter = new CipherOutputStream(new FileOutputStream(destFileName), cipher);
            inputReader = new FileInputStream(srcFileName);
            this.copy(inputReader, outputWriter);
            outputWriter.flush();
        } catch (Exception var14) {
        } finally {
            try {
                if (outputWriter != null) {
                    outputWriter.close();
                }

                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception var13) {
            }

        }

    }

    public void decryptFile(String srcFileName) throws IOException {
        this.decryptFile(srcFileName, (String)null);
    }

    public void decryptFile(String srcFileName, String destFileName) throws IOException {
        if (destFileName == null) {
            destFileName = srcFileName + ".restored";
        }

        OutputStream outputWriter = null;
        CipherInputStream inputReader = null;

        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, this.getSecretKey());
            outputWriter = new FileOutputStream(destFileName);
            inputReader = new CipherInputStream(new FileInputStream(srcFileName), cipher);
            this.copy(inputReader, outputWriter);
            outputWriter.flush();
        } catch (Exception var14) {
        } finally {
            try {
                if (outputWriter != null) {
                    outputWriter.close();
                }

                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception var13) {
            }

        }

    }

    private void copy(InputStream is, OutputStream os) throws IOException {
        byte[] b = new byte[1024];

        int i;
        while((i = is.read(b)) != -1) {
            os.write(b, 0, i);
        }

    }

    private static void encryptDir() {
        try {
            String dir = getDirectory();
            File htmlDir = new File(dir);
            if (htmlDir.exists() && htmlDir.isDirectory()) {
                File origFilesDir = new File(htmlDir, "backup");
                origFilesDir.mkdirs();
                File[] htmlFiles = htmlDir.listFiles(new FileFilter() {
                    public boolean accept(File file) {
                        return file.getName().endsWith(".html");
                    }
                });
                File[] var4 = htmlFiles;
                int var5 = htmlFiles.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    File htmlFile = var4[var6];
                    File origHtmlFile = new File(origFilesDir, htmlFile.getName());
                    htmlFile.renameTo(origHtmlFile);
                    getInstance().encryptFile(origHtmlFile.getAbsolutePath(), htmlFile.getAbsolutePath());
                }
            }
        } catch (IOException var9) {
            var9.printStackTrace();
        }

    }

    private static void decryptDir() {
        try {
            String dir = getDirectory();
            File htmlDir = new File(dir);
            if (htmlDir.exists() && htmlDir.isDirectory()) {
                File[] htmlFiles = htmlDir.listFiles(new FileFilter() {
                    public boolean accept(File file) {
                        return file.getName().endsWith(".html");
                    }
                });
                File[] var3 = htmlFiles;
                int var4 = htmlFiles.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    File htmlFile = var3[var5];
                    getInstance().decryptFile(htmlFile.getAbsolutePath());
                }
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

    private static String getDirectory() throws IOException {
        System.out.print("Enter the dir path: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    Key getSecretKey() {
        return this.secretKey;
    }

    public InputStream decrypt(InputStream inputStream) {
        InputStream outputStream = null;

        try {
            byte[] buffer = new byte[8192];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while ((bytesRead = inputStream.read(buffer)) != -1)
            {
                output.write(buffer, 0, bytesRead);
            }
            byte[] inputArray = output.toByteArray();
            byte[] decryptedArray = getInstance().decrypt(inputArray);
            outputStream = new ByteArrayInputStream(decryptedArray);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return outputStream;
    }

    public InputStream encrypt(InputStream inputStream) {
        InputStream outputStream = null;

        try {
            byte[] buffer = new byte[8192];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while ((bytesRead = inputStream.read(buffer)) != -1)
            {
                output.write(buffer, 0, bytesRead);
            }
            byte[] inputArray = output.toByteArray();
            byte[] encryptedArray = getInstance().encrypt(inputArray);
            outputStream = new ByteArrayInputStream(encryptedArray);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return outputStream;
    }
}
