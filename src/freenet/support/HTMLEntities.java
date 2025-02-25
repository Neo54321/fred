/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */
package freenet.support;

import java.util.HashMap;

/**
 * Class that provides data structures filled with HTML Entities and correspondent char value
 * 
 * @author Alberto Bacchelli &lt;sback@freenetproject.org&gt;
 */
public final class HTMLEntities {

	/**
	 * a Map where the HTML Entity is the value and the correspondent char is the key
	 */
	public static final HashMap<Character, String> encodeMap;

	/**
	 * a Map where the HTML Entity is the key and the correspondent char is the value
	 */
	public static final HashMap<String, Character> decodeMap;
	
	private static final Object[][] charArray = {
		{(char) 0, "#0"},
		{(char) 34, "quot"},
		{(char) 38, "amp"},
		{(char) 39, "#39"},
		{(char) 60, "lt"},
		{(char) 62, "gt"},
		{(char) 160, "nbsp"},
		{(char) 161, "iexcl"},
		{(char) 162, "cent"},
		{(char) 163, "pound"},
		{(char) 164, "curren"},
		{(char) 165, "yen"},
		{(char) 166, "brvbar"},
		{(char) 167, "sect"},
		{(char) 168, "uml"},
		{(char) 169, "copy"},
		{(char) 170, "ordf"},
		{(char) 171, "laquo"},
		{(char) 172, "not"},
		{(char) 173, "shy"},
		{(char) 174, "reg"},
		{(char) 175, "macr"},
		{(char) 176, "deg"},
		{(char) 177, "plusmn"},
		{(char) 178, "sup2"},
		{(char) 179, "sup3"},
		{(char) 180, "acute"},
		{(char) 181, "micro"},
		{(char) 182, "para"},
		{(char) 183, "middot"},
		{(char) 184, "cedil"},
		{(char) 185, "sup1"},
		{(char) 186, "ordm"},
		{(char) 187, "raquo"},
		{(char) 188, "frac14"},
		{(char) 189, "frac12"},
		{(char) 190, "frac34"},
		{(char) 191, "iquest"},
		{(char) 192, "Agrave"},
		{(char) 193, "Aacute"},
		{(char) 194, "Acirc"},
		{(char) 195, "Atilde"},
		{(char) 196, "Auml"},
		{(char) 197, "Aring"},
		{(char) 198, "AElig"},
		{(char) 199, "Ccedil"},
		{(char) 200, "Egrave"},
		{(char) 201, "Eacute"},
		{(char) 202, "Ecirc"},
		{(char) 203, "Euml"},
		{(char) 204, "Igrave"},
		{(char) 205, "Iacute"},
		{(char) 206, "Icirc"},
		{(char) 207, "Iuml"},
		{(char) 208, "ETH"},
		{(char) 209, "Ntilde"},
		{(char) 210, "Ograve"},
		{(char) 211, "Oacute"},
		{(char) 212, "Ocirc"},
		{(char) 213, "Otilde"},
		{(char) 214, "Ouml"},
		{(char) 215, "times"},
		{(char) 216, "Oslash"},
		{(char) 217, "Ugrave"},
		{(char) 218, "Uacute"},
		{(char) 219, "Ucirc"},
		{(char) 220, "Uuml"},
		{(char) 221, "Yacute"},
		{(char) 222, "THORN"},
		{(char) 223, "szlig"},
		{(char) 224, "agrave"},
		{(char) 225, "aacute"},
		{(char) 226, "acirc"},
		{(char) 227, "atilde"},
		{(char) 228, "auml"},
		{(char) 229, "aring"},
		{(char) 230, "aelig"},
		{(char) 231, "ccedil"},
		{(char) 232, "egrave"},
		{(char) 233, "eacute"},
		{(char) 234, "ecirc"},
		{(char) 235, "euml"},
		{(char) 236, "igrave"},
		{(char) 237, "iacute"},
		{(char) 238, "icirc"},
		{(char) 239, "iuml"},
		{(char) 240, "eth"},
		{(char) 241, "ntilde"},
		{(char) 242, "ograve"},
		{(char) 243, "oacute"},
		{(char) 244, "ocirc"},
		{(char) 245, "otilde"},
		{(char) 246, "ouml"},
		{(char) 247, "divide"},
		{(char) 248, "oslash"},
		{(char) 249, "ugrave"},
		{(char) 250, "uacute"},
		{(char) 251, "ucirc"},
		{(char) 252, "uuml"},
		{(char) 253, "yacute"},
		{(char) 254, "thorn"},
		{(char) 255, "yuml"},
		{(char) 260, "#260"},
		{(char) 261, "#261"},
		{(char) 262, "#262"},
		{(char) 263, "#263"},
		{(char) 280, "#280"},
		{(char) 281, "#281"},
		{(char) 321, "#321"},
		{(char) 322, "#322"},
		{(char) 323, "#323"},
		{(char) 324, "#324"},
		{(char) 338, "OElig"},
		{(char) 339, "oelig"},
		{(char) 346, "#346"},
		{(char) 347, "#347"},
		{(char) 352, "Scaron"},
		{(char) 353, "scaron"},
		{(char) 376, "Yuml"},
		{(char) 377, "#377"},
		{(char) 378, "#378"},
		{(char) 379, "#379"},
		{(char) 380, "#380"},
		{(char) 402, "fnof"},
		{(char) 710, "circ"},
		{(char) 732, "tilde"},
		{(char) 913, "Alpha"},
		{(char) 914, "Beta"},
		{(char) 915, "Gamma"},
		{(char) 916, "Delta"},
		{(char) 917, "Epsilon"},
		{(char) 918, "Zeta"},
		{(char) 919, "Eta"},
		{(char) 920, "Theta"},
		{(char) 921, "Iota"},
		{(char) 922, "Kappa"},
		{(char) 923, "Lambda"},
		{(char) 924, "Mu"},
		{(char) 925, "Nu"},
		{(char) 926, "Xi"},
		{(char) 927, "Omicron"},
		{(char) 928, "Pi"},
		{(char) 929, "Rho"},
		{(char) 931, "Sigma"},
		{(char) 932, "Tau"},
		{(char) 933, "Upsilon"},
		{(char) 934, "Phi"},
		{(char) 935, "Chi"},
		{(char) 936, "Psi"},
		{(char) 937, "Omega"},
		{(char) 945, "alpha"},
		{(char) 946, "beta"},
		{(char) 947, "gamma"},
		{(char) 948, "delta"},
		{(char) 949, "epsilon"},
		{(char) 950, "zeta"},
		{(char) 951, "eta"},
		{(char) 952, "theta"},
		{(char) 953, "iota"},
		{(char) 954, "kappa"},
		{(char) 955, "lambda"},
		{(char) 956, "mu"},
		{(char) 957, "nu"},
		{(char) 958, "xi"},
		{(char) 959, "omicron"},
		{(char) 960, "pi"},
		{(char) 961, "rho"},
		{(char) 962, "sigmaf"},
		{(char) 963, "sigma"},
		{(char) 964, "tau"},
		{(char) 965, "upsilon"},
		{(char) 966, "phi"},
		{(char) 967, "chi"},
		{(char) 968, "psi"},
		{(char) 969, "omega"},
		{(char) 977, "thetasym"},
		{(char) 978, "upsih"},
		{(char) 982, "piv"},
		{(char) 8194, "ensp"},
		{(char) 8195, "emsp"},
		{(char) 8201, "thinsp"},
		{(char) 8204, "zwnj"},
		{(char) 8205, "zwj"},
		{(char) 8206, "lrm"},
		{(char) 8207, "rlm"},
		{(char) 8211, "ndash"},
		{(char) 8212, "mdash"},
		{(char) 8216, "lsquo"},
		{(char) 8217, "rsquo"},
		{(char) 8218, "sbquo"},
		{(char) 8220, "ldquo"},
		{(char) 8221, "rdquo"},
		{(char) 8222, "bdquo"},
		{(char) 8224, "dagger"},
		{(char) 8225, "Dagger"},
		{(char) 8226, "bull"},
		{(char) 8230, "hellip"},
		{(char) 8240, "permil"},
		{(char) 8242, "prime"},
		{(char) 8243, "Prime"},
		{(char) 8249, "lsaquo"},
		{(char) 8250, "rsaquo"},
		{(char) 8254, "oline"},
		{(char) 8260, "frasl"},
		{(char) 8364, "euro"},
		{(char) 8465, "image"},
		{(char) 8472, "weierp"},
		{(char) 8476, "real"},
		{(char) 8482, "trade"},
		{(char) 8501, "alefsym"},
		{(char) 8592, "larr"},
		{(char) 8593, "uarr"},
		{(char) 8594, "rarr"},
		{(char) 8595, "darr"},
		{(char) 8596, "harr"},
		{(char) 8629, "crarr"},
		{(char) 8656, "lArr"},
		{(char) 8657, "uArr"},
		{(char) 8658, "rArr"},
		{(char) 8659, "dArr"},
		{(char) 8660, "hArr"},
		{(char) 8704, "forall"},
		{(char) 8706, "part"},
		{(char) 8707, "exist"},
		{(char) 8709, "empty"},
		{(char) 8711, "nabla"},
		{(char) 8712, "isin"},
		{(char) 8713, "notin"},
		{(char) 8715, "ni"},
		{(char) 8719, "prod"},
		{(char) 8721, "sum"},
		{(char) 8722, "minus"},
		{(char) 8727, "lowast"},
		{(char) 8730, "radic"},
		{(char) 8733, "prop"},
		{(char) 8734, "infin"},
		{(char) 8736, "ang"},
		{(char) 8743, "and"},
		{(char) 8744, "or"},
		{(char) 8745, "cap"},
		{(char) 8746, "cup"},
		{(char) 8747, "int"},
		{(char) 8756, "there4"},
		{(char) 8764, "sim"},
		{(char) 8773, "cong"},
		{(char) 8776, "asymp"},
		{(char) 8800, "ne"},
		{(char) 8801, "equiv"},
		{(char) 8804, "le"},
		{(char) 8805, "ge"},
		{(char) 8834, "sub"},
		{(char) 8835, "sup"},
		{(char) 8836, "nsub"},
		{(char) 8838, "sube"},
		{(char) 8839, "supe"},
		{(char) 8853, "oplus"},
		{(char) 8855, "otimes"},
		{(char) 8869, "perp"},
		{(char) 8901, "sdot"},
		{(char) 8968, "lceil"},
		{(char) 8969, "rceil"},
		{(char) 8970, "lfloor"},
		{(char) 8971, "rfloor"},
		{(char) 9001, "lang"},
		{(char) 9002, "rang"},
		{(char) 9674, "loz"},
		{(char) 9824, "spades"},
		{(char) 9827, "clubs"},
		{(char) 9829, "hearts"},
		{(char) 9830, "diams"}
	};
	
	
	static {
		encodeMap = new HashMap<Character, String>();
		decodeMap = new HashMap<String, Character>();
		
		for(Object[] ch: charArray) {
			encodeMap.put((Character) ch[0], (String) ch[1]);
			decodeMap.put((String) ch[1], (Character) ch[0]);
		}
		
	}

}
