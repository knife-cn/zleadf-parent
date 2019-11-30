/**
 * Encrypt message by DES in ECB mode and Pkcs7 padding scheme
 *
 * NOTE: DES is weak, please use 3DES(Triple DES) or AES
 * 
 * @param  {String} message
 * @param  {String} key
 * @return {String} ciphertext(base64 string)
 * 
 * @author Sun
 * @version 2013-5-15
 *
 * @see <a href="https://groups.google.com/d/msg/crypto-js/I378fq3esK8/HZ2P2Xtuzk8J">des encrypion: js encrypted value does not match the java encrypted value</a>
 * In cryptoJS you have to convert the key to hex
 * and useit as word just like above (otherwise it will be considered as passphrase)
 * 
 * @see <a href="http://stackoverflow.com/questions/12894722/c-sharp-and-java-des-encryption-value-are-not-identical">C# and Java DES Encryption value are not identical</a>
 * SunJCE provider uses ECB as the default mode,
 * and PKCS5Padding as the default padding scheme for DES.(JCA Doc)
 * This means that in the case of the SunJCE provider,
 *     Cipher c1 = Cipher.getInstance("DES/ECB/PKCS5Padding");
 * and
 *     Cipher c1 = Cipher.getInstance("DES");
 * are equivalent statements.
 *
 * @see <a href="http://stackoverflow.com/questions/10193567/java-security-nosuchalgorithmexception-cannot-find-any-provider-supporting-aes">java.security.NoSuchAlgorithmException: Cannot find any provider supporting AES/ECB/PKCS7PADDING</a>
 * I will point out that PKCS#5 and PKCS#7 actually specify exactly
 * the same type of padding (they are the same!),
 * but it's called #5 when used in this context. :)
 */
function encryptByDES(message, key) {
    // For the key, when you pass a string,
    // it's treated as a passphrase and used to derive an actual key and IV.
    // Or you can pass a WordArray that represents the actual key.
    // If you pass the actual key, you must also pass the actual IV.
    var keyHex = CryptoJS.enc.Utf8.parse(key);
    //console.log(CryptoJS.enc.Utf8.stringify(keyHex), CryptoJS.enc.Hex.stringify(keyHex));
    //console.log(CryptoJS.enc.Hex.parse(CryptoJS.enc.Utf8.parse(key).toString(CryptoJS.enc.Hex)));
 
    // CryptoJS use CBC as the default mode, and Pkcs7 as the default padding scheme
    var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    // decrypt encrypt result
    // var decrypted = CryptoJS.DES.decrypt(encrypted, keyHex, {
    //     mode: CryptoJS.mode.ECB,
    //     padding: CryptoJS.pad.Pkcs7
    // });
    // console.log(decrypted.toString(CryptoJS.enc.Utf8));
 
    // when mode is CryptoJS.mode.CBC (default mode), you must set iv param
    // var iv = 'inputvec';
    // var ivHex = CryptoJS.enc.Hex.parse(CryptoJS.enc.Utf8.parse(iv).toString(CryptoJS.enc.Hex));
    // var encrypted = CryptoJS.DES.encrypt(message, keyHex, { iv: ivHex, mode: CryptoJS.mode.CBC });
    // var decrypted = CryptoJS.DES.decrypt(encrypted, keyHex, { iv: ivHex, mode: CryptoJS.mode.CBC });
 
    // console.log('encrypted.toString()  -> base64(ciphertext)  :', encrypted.toString());
    // console.log('base64(ciphertext)    <- encrypted.toString():', encrypted.ciphertext.toString(CryptoJS.enc.Base64));
    // console.log('ciphertext.toString() -> ciphertext hex      :', encrypted.ciphertext.toString());
    var str =  encrypted.toString();
   str=str.replace("+","[j]");
   str=str.replace(" ","");
    return str;
}
 
/**
 * Decrypt ciphertext by DES in ECB mode and Pkcs7 padding scheme
 * 
 * @param  {String} ciphertext(base64 string)
 * @param  {String} key
 * @return {String} plaintext
 *
 * @author Sun
 * @version 2013-5-15
 */
function decryptByDES(ciphertext, key) {
    var keyHex = CryptoJS.enc.Utf8.parse(key);
 
    var decrypted = CryptoJS.DES.decrypt({
        ciphertext: CryptoJS.enc.Base64.parse(ciphertext)
    }, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
 
    return decrypted.toString(CryptoJS.enc.Utf8);
}