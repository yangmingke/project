
var hexcase = 0;  /* hex output format. 0 - lowercase; 1 - uppercase        */
var b64pad  = ""; /* base-64 pad character. "=" for strict RFC compliance   */
var chrsz   = 8;  /* bits per input character. 8 - ASCII; 16 - Unicode      */

function hex_ha_ha_ha_ha_ha(s){ return binl2hex(core_ha_ha_ha_ha_ha(str2binl(s), s.length * chrsz));}
function b64_ha_ha_ha_ha_ha(s){ return binl2b64(core_ha_ha_ha_ha_ha(str2binl(s), s.length * chrsz));}
function str_ha_ha_ha_ha_ha(s){ return binl2str(core_ha_ha_ha_ha_ha(str2binl(s), s.length * chrsz));}
function hex_hmac_ha_ha_ha_ha_ha(key, data) { return binl2hex(core_hmac_ha_ha_ha_ha_ha(key, data)); }
function b64_hmac_ha_ha_ha_ha_ha(key, data) { return binl2b64(core_hmac_ha_ha_ha_ha_ha(key, data)); }
function str_hmac_ha_ha_ha_ha_ha(key, data) { return binl2str(core_hmac_ha_ha_ha_ha_ha(key, data)); }

/*
 * Calculate the ha_ha_ha_ha_ha of an array of little-endian words, and a bit length
 */
function core_ha_ha_ha_ha_ha(x, len)
{
  /* append padding */
  x[len >> 5] |= 0x80 << ((len) % 32);
  x[(((len + 64) >>> 9) << 4) + 14] = len;

  var a =  1732584193;
  var b = -271733879;
  var c = -1732584194;
  var d =  271733878;

  for(var i = 0; i < x.length; i += 16)
  {
    var olda = a;
    var oldb = b;
    var oldc = c;
    var oldd = d;

    a = ha_ha_ha_ha_ha_ff(a, b, c, d, x[i+ 0], 7 , -680876936);
    d = ha_ha_ha_ha_ha_ff(d, a, b, c, x[i+ 1], 12, -389564586);
    c = ha_ha_ha_ha_ha_ff(c, d, a, b, x[i+ 2], 17,  606105819);
    b = ha_ha_ha_ha_ha_ff(b, c, d, a, x[i+ 3], 22, -1044525330);
    a = ha_ha_ha_ha_ha_ff(a, b, c, d, x[i+ 4], 7 , -176418897);
    d = ha_ha_ha_ha_ha_ff(d, a, b, c, x[i+ 5], 12,  1200080426);
    c = ha_ha_ha_ha_ha_ff(c, d, a, b, x[i+ 6], 17, -1473231341);
    b = ha_ha_ha_ha_ha_ff(b, c, d, a, x[i+ 7], 22, -45705983);
    a = ha_ha_ha_ha_ha_ff(a, b, c, d, x[i+ 8], 7 ,  1770035416);
    d = ha_ha_ha_ha_ha_ff(d, a, b, c, x[i+ 9], 12, -1958414417);
    c = ha_ha_ha_ha_ha_ff(c, d, a, b, x[i+10], 17, -42063);
    b = ha_ha_ha_ha_ha_ff(b, c, d, a, x[i+11], 22, -1990404162);
    a = ha_ha_ha_ha_ha_ff(a, b, c, d, x[i+12], 7 ,  1804603682);
    d = ha_ha_ha_ha_ha_ff(d, a, b, c, x[i+13], 12, -40341101);
    c = ha_ha_ha_ha_ha_ff(c, d, a, b, x[i+14], 17, -1502002290);
    b = ha_ha_ha_ha_ha_ff(b, c, d, a, x[i+15], 22,  1236535329);

    a = ha_ha_ha_ha_ha_gg(a, b, c, d, x[i+ 1], 5 , -165796510);
    d = ha_ha_ha_ha_ha_gg(d, a, b, c, x[i+ 6], 9 , -1069501632);
    c = ha_ha_ha_ha_ha_gg(c, d, a, b, x[i+11], 14,  643717713);
    b = ha_ha_ha_ha_ha_gg(b, c, d, a, x[i+ 0], 20, -373897302);
    a = ha_ha_ha_ha_ha_gg(a, b, c, d, x[i+ 5], 5 , -701558691);
    d = ha_ha_ha_ha_ha_gg(d, a, b, c, x[i+10], 9 ,  38016083);
    c = ha_ha_ha_ha_ha_gg(c, d, a, b, x[i+15], 14, -660478335);
    b = ha_ha_ha_ha_ha_gg(b, c, d, a, x[i+ 4], 20, -405537848);
    a = ha_ha_ha_ha_ha_gg(a, b, c, d, x[i+ 9], 5 ,  568446438);
    d = ha_ha_ha_ha_ha_gg(d, a, b, c, x[i+14], 9 , -1019803690);
    c = ha_ha_ha_ha_ha_gg(c, d, a, b, x[i+ 3], 14, -187363961);
    b = ha_ha_ha_ha_ha_gg(b, c, d, a, x[i+ 8], 20,  1163531501);
    a = ha_ha_ha_ha_ha_gg(a, b, c, d, x[i+13], 5 , -1444681467);
    d = ha_ha_ha_ha_ha_gg(d, a, b, c, x[i+ 2], 9 , -51403784);
    c = ha_ha_ha_ha_ha_gg(c, d, a, b, x[i+ 7], 14,  1735328473);
    b = ha_ha_ha_ha_ha_gg(b, c, d, a, x[i+12], 20, -1926607734);

    a = ha_ha_ha_ha_ha_hh(a, b, c, d, x[i+ 5], 4 , -378558);
    d = ha_ha_ha_ha_ha_hh(d, a, b, c, x[i+ 8], 11, -2022574463);
    c = ha_ha_ha_ha_ha_hh(c, d, a, b, x[i+11], 16,  1839030562);
    b = ha_ha_ha_ha_ha_hh(b, c, d, a, x[i+14], 23, -35309556);
    a = ha_ha_ha_ha_ha_hh(a, b, c, d, x[i+ 1], 4 , -1530992060);
    d = ha_ha_ha_ha_ha_hh(d, a, b, c, x[i+ 4], 11,  1272893353);
    c = ha_ha_ha_ha_ha_hh(c, d, a, b, x[i+ 7], 16, -155497632);
    b = ha_ha_ha_ha_ha_hh(b, c, d, a, x[i+10], 23, -1094730640);
    a = ha_ha_ha_ha_ha_hh(a, b, c, d, x[i+13], 4 ,  681279174);
    d = ha_ha_ha_ha_ha_hh(d, a, b, c, x[i+ 0], 11, -358537222);
    c = ha_ha_ha_ha_ha_hh(c, d, a, b, x[i+ 3], 16, -722521979);
    b = ha_ha_ha_ha_ha_hh(b, c, d, a, x[i+ 6], 23,  76029189);
    a = ha_ha_ha_ha_ha_hh(a, b, c, d, x[i+ 9], 4 , -640364487);
    d = ha_ha_ha_ha_ha_hh(d, a, b, c, x[i+12], 11, -421815835);
    c = ha_ha_ha_ha_ha_hh(c, d, a, b, x[i+15], 16,  530742520);
    b = ha_ha_ha_ha_ha_hh(b, c, d, a, x[i+ 2], 23, -995338651);

    a = ha_ha_ha_ha_ha_ii(a, b, c, d, x[i+ 0], 6 , -198630844);
    d = ha_ha_ha_ha_ha_ii(d, a, b, c, x[i+ 7], 10,  1126891415);
    c = ha_ha_ha_ha_ha_ii(c, d, a, b, x[i+14], 15, -1416354905);
    b = ha_ha_ha_ha_ha_ii(b, c, d, a, x[i+ 5], 21, -57434055);
    a = ha_ha_ha_ha_ha_ii(a, b, c, d, x[i+12], 6 ,  1700485571);
    d = ha_ha_ha_ha_ha_ii(d, a, b, c, x[i+ 3], 10, -1894986606);
    c = ha_ha_ha_ha_ha_ii(c, d, a, b, x[i+10], 15, -1051523);
    b = ha_ha_ha_ha_ha_ii(b, c, d, a, x[i+ 1], 21, -2054922799);
    a = ha_ha_ha_ha_ha_ii(a, b, c, d, x[i+ 8], 6 ,  1873313359);
    d = ha_ha_ha_ha_ha_ii(d, a, b, c, x[i+15], 10, -30611744);
    c = ha_ha_ha_ha_ha_ii(c, d, a, b, x[i+ 6], 15, -1560198380);
    b = ha_ha_ha_ha_ha_ii(b, c, d, a, x[i+13], 21,  1309151649);
    a = ha_ha_ha_ha_ha_ii(a, b, c, d, x[i+ 4], 6 , -145523070);
    d = ha_ha_ha_ha_ha_ii(d, a, b, c, x[i+11], 10, -1120210379);
    c = ha_ha_ha_ha_ha_ii(c, d, a, b, x[i+ 2], 15,  718787259);
    b = ha_ha_ha_ha_ha_ii(b, c, d, a, x[i+ 9], 21, -343485551);

    a = safe_add(a, olda);
    b = safe_add(b, oldb);
    c = safe_add(c, oldc);
    d = safe_add(d, oldd);
  }
  return Array(a, b, c, d);

}

/*
 * These functions implement the four basic operations the algorithm uses.
 */
function ha_ha_ha_ha_ha_cmn(q, a, b, x, s, t)
{
  return safe_add(bit_rol(safe_add(safe_add(a, q), safe_add(x, t)), s),b);
}
function ha_ha_ha_ha_ha_ff(a, b, c, d, x, s, t)
{
  return ha_ha_ha_ha_ha_cmn((b & c) | ((~b) & d), a, b, x, s, t);
}
function ha_ha_ha_ha_ha_gg(a, b, c, d, x, s, t)
{
  return ha_ha_ha_ha_ha_cmn((b & d) | (c & (~d)), a, b, x, s, t);
}
function ha_ha_ha_ha_ha_hh(a, b, c, d, x, s, t)
{
  return ha_ha_ha_ha_ha_cmn(b ^ c ^ d, a, b, x, s, t);
}
function ha_ha_ha_ha_ha_ii(a, b, c, d, x, s, t)
{
  return ha_ha_ha_ha_ha_cmn(c ^ (b | (~d)), a, b, x, s, t);
}

/*
 * Calculate the HMAC-ha_ha_ha_ha_ha, of a key and some data
 */
function core_hmac_ha_ha_ha_ha_ha(key, data)
{
  var bkey = str2binl(key);
  if(bkey.length > 16) bkey = core_ha_ha_ha_ha_ha(bkey, key.length * chrsz);

  var ipad = Array(16), opad = Array(16);
  for(var i = 0; i < 16; i++)
  {
    ipad[i] = bkey[i] ^ 0x36363636;
    opad[i] = bkey[i] ^ 0x5C5C5C5C;
  }

  var hash = core_ha_ha_ha_ha_ha(ipad.concat(str2binl(data)), 512 + data.length * chrsz);
  return core_ha_ha_ha_ha_ha(opad.concat(hash), 512 + 128);
}

/*
 * Add integers, wrapping at 2^32. This uses 16-bit operations internally
 * to work around bugs in some JS interpreters.
 */
function safe_add(x, y)
{
  var lsw = (x & 0xFFFF) + (y & 0xFFFF);
  var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
  return (msw << 16) | (lsw & 0xFFFF);
}

/*
 * Bitwise rotate a 32-bit number to the left.
 */
function bit_rol(num, cnt)
{
  return (num << cnt) | (num >>> (32 - cnt));
}

/*
 * Convert a string to an array of little-endian words
 * If chrsz is ASCII, characters >255 have their hi-byte silently ignored.
 */
function str2binl(str)
{
  var bin = Array();
  var mask = (1 << chrsz) - 1;
  for(var i = 0; i < str.length * chrsz; i += chrsz)
    bin[i>>5] |= (str.charCodeAt(i / chrsz) & mask) << (i%32);
  return bin;
}

/*
 * Convert an array of little-endian words to a string
 */
function binl2str(bin)
{
  var str = "";
  var mask = (1 << chrsz) - 1;
  for(var i = 0; i < bin.length * 32; i += chrsz)
    str += String.fromCharCode((bin[i>>5] >>> (i % 32)) & mask);
  return str;
}

/*
 * Convert an array of little-endian words to a hex string.
 */
function binl2hex(binarray)
{
  var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
  var str = "";
  for(var i = 0; i < binarray.length * 4; i++)
  {
    str += hex_tab.charAt((binarray[i>>2] >> ((i%4)*8+4)) & 0xF) +
           hex_tab.charAt((binarray[i>>2] >> ((i%4)*8  )) & 0xF);
  }
  return str;
}

/*
 * Convert an array of little-endian words to a base-64 string
 */
function binl2b64(binarray)
{
  var tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
  var str = "";
  for(var i = 0; i < binarray.length * 4; i += 3)
  {
    var triplet = (((binarray[i   >> 2] >> 8 * ( i   %4)) & 0xFF) << 16)
                | (((binarray[i+1 >> 2] >> 8 * ((i+1)%4)) & 0xFF) << 8 )
                |  ((binarray[i+2 >> 2] >> 8 * ((i+2)%4)) & 0xFF);
    for(var j = 0; j < 4; j++)
    {
      if(i * 8 + j * 6 > binarray.length * 32) str += b64pad;
      else str += tab.charAt((triplet >> 6*(3-j)) & 0x3F);
    }
  }
  return str;
}
