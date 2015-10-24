package com.fdc.boarding.releasetracker.common.security;


import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.releasetracker.common.security.exception.CypherException;

@Converter
public class EncryptionProvider implements AttributeConverter<String, String> {
	private static final String			passphrase		= "lv84g@Kt3d*B9kls0f;?235f";
	protected LoggerProxy               logger          = LoggerProxy.getLogger( getClass() );
    private SecretKeyFactory            keyFactory;
    private DESedeKeySpec               keySpec;
    private SecretKey                   secretKey;

    private Cipher                      encipher;
    private Cipher                      decipher;

    /**
     * 
     */
    public EncryptionProvider(
    )
    {
        //
        // Default constructor.
        //
        super();
    }
    
    /**
     * @param rawValue
     * @param encryptedValue
     * @return
     */
    public boolean compareValues(
      String                            rawValue
    , String                            encryptedValue
    )
    {
        boolean                         same            = false;
        String                          decrypted;
        
        if( rawValue == null && encryptedValue == null )
        {
            same    = true;
        }
        else if( ( rawValue != null && encryptedValue == null ) || ( rawValue == null && encryptedValue != null ) )
        {
            same    = false;   
        }
        else if( rawValue != null && encryptedValue != null )
        {
            decrypted   = decrypt( encryptedValue );
            same        = rawValue.equals( decrypted );
        }
        
        return same;
    }
    
    @PostConstruct
    public void init(
    )
    {
        try
        {
            keySpec    = new DESedeKeySpec( passphrase.getBytes( "UTF8" ) );
            keyFactory = SecretKeyFactory.getInstance( "DESede" );
            secretKey  = keyFactory.generateSecret( keySpec );

            encipher   = Cipher.getInstance( "DESede/ECB/PKCS5Padding" );
            encipher.init( Cipher.ENCRYPT_MODE, secretKey );
            decipher   = Cipher.getInstance( "DESede/ECB/PKCS5Padding" );
            decipher.init( Cipher.DECRYPT_MODE, secretKey );
        }
        catch( Exception e )
        {
            logger.fatal( "Unable to initialize EncryptedType - " + e );
        }
    }

    /**
     * Translate a byte array to a hex string.
     * Note that 'odd length' values are not supported.
     *
     * @param   bytes
     *      Source byte array.
     *
     * @return
     *      Hexadecimal string representation of byte array.
     */
    protected String bytesToHexString(
      byte[]                            bytes
    )
    {
        final char                      hexTbl[];
        char                            hexBuf[];
        
        hexTbl  = "0123456789ABCDEF".toCharArray();

        //
        // Translate two nibbles at a time until all nibbles have been
        // translated or there is only one nibble left.
        //
        hexBuf  = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++)
        {
            hexBuf[i * 2]      = hexTbl[(bytes[i] & 0xf0)>>4];
            hexBuf[(i * 2)+ 1] = hexTbl[(bytes[i] & 0x0f)   ];
        }

        return new String( hexBuf );
    }


    /**
     * Translate a hex string to a byte array.
     *
     * @param   hexStr
     *      Source hex string.
     *
     * @return  Byte array.
     *      If the source hex string is not valid, then null is returned.
     */
    protected byte[] hexStringToBytes(
      String                            hexStr
    )
    {
        byte[]                          retval;
        int                             val;
        
        //
        //  hex string must contain 'chunks' of byte values
        //
        if( hexStr.length() % 2 != 0 )
        {
            return null;
        }

        //
        //  convert string
        //
        retval = new byte[hexStr.length() / 2];
        try
        {
            for (int i = 0; i < retval.length; i++)
            {
                val         = Integer.parseInt( hexStr.substring( (i * 2), ((i * 2) + 2) ), 16 );
                retval[i]   = 0;
                retval[i]   |= val;
            }
        }
        catch( NumberFormatException e )
        {
            return null;
        }

        return retval;
    }
    
    /** 
     * decrypt
     * 
     * Perform decryption.
     * 
     * @param   encryptedStr          
     *      Data to be decrypted.
     * 
     * @return  Decrypted data. 
     */ 
    public String decrypt( 
      String                            encryptedStr 
    )
    { 
        byte[]                          encryptedBytes;
        byte[]                          decryptedBytes = null; 
        String                          value;
       
        try 
        { 
        	encryptedBytes  = hexStringToBytes(encryptedStr);
        	decryptedBytes  = decipher.doFinal( encryptedBytes ); 
        	value           = new String( decryptedBytes ); 
        } 
        catch(Exception e) 
        {
            //
            // Throw exception.
            //
        	e.printStackTrace();
            throw CypherException.create( logger
                                        , "Decryption failed"
                                        , e
                                        , "decrypt"
                                        , EncryptionProvider.class
                                        , CypherException.PASSWORD_DECRYPT
            );
        }

        return value; 
    } 
    
    /** 
     * encrypt
     * 
     * Perform encryption.
     * 
     * @param   clearStr               
     *      Data to be encrypted.
     * 
     * @return  Encrypted data.
     */ 
    public String encrypt( 
      String                            clearStr 
    )
    { 
        byte[]                          encryptedBytes = null; 
        String                          value;
        
        try 
        { 
        	encryptedBytes  = encipher.doFinal( clearStr.getBytes( "UTF8" ) ); 
        	value           = bytesToHexString( encryptedBytes );
        } 
        catch( Exception e ) 
        {
            //
            // Throw exception.
            //
            throw CypherException.create( logger
                                        , "Encryption failed"
                                        , e
                                        , "encrypt"
                                        , EncryptionProvider.class
                                        , CypherException.PASSWORD_ENCRYPT
            );
        }

        return value; 
    }

	@Override
	public String convertToDatabaseColumn(String attribute) {
		init();
		return encrypt( attribute );
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		init();
		return decrypt( dbData );
	} 

}
