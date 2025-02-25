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

package freenet.io;

import java.util.regex.Pattern;

/**
 * Identifies numeric IP addresses. This class is currently capable of
 * recognizing:
 * <ul>
 * <li>IPv4 unabridged (a.b.c.d)</li>
 * <li>IPv4 abridged (a.b.d or a.d)</li>
 * <li>IPv6 unabridged (a:b:c:d:e:f:g:h)</li>
 * <li>IPv6 abridged (a::b:c:d:e)</li>
 * </ul>
 * 
 * @author David Roden &lt;droden@gmail.com&gt;
 * @version $Id$
 */
public class AddressIdentifier {
	public static final Pattern ipv4Pattern, ipv6Pattern, ipv6PatternWithPercentScopeID, ipv6ISATAPPattern;
	
	static {
		String byteRegex = "(?>2[0-4][0-9]|25[0-5]|[01]?[0-9]?[0-9]?)";
		String ipv4AddressRegex = byteRegex + "\\.(?>" + byteRegex + "\\.)?(?>" + byteRegex + "\\.)?" + byteRegex;
		ipv4Pattern = Pattern.compile(ipv4AddressRegex);
		
		String wordRegex = "(?>[0-9a-f]{1,4})";
		String percentScopeIDRegex = "(?>%[0-9]{1,3})?";
		/*
		 * ::(?>(?>X:){0,6}X)?
		 * X::(?>(?>X:){0,5}X)?
		 * X:X::(?>(?>X:){0,4}X)?
		 * X:X:X::(?>(?>X:){0,3}X)?
		 * (?>X:){4}:(?>(?>X:){0,2}X)?
		 * (?>X:){5}:(?>X:)?X?
		 * (?>X:){6}:X?
		 * (?>X:){7}(?>X|:)
		 */
		String ipv6AddressRegex = "::(?>(?>X:){0,6}X)?|X::(?>(?>X:){0,5}X)?|X:X::(?>(?>X:){0,4}X)?|X:X:X::(?>(?>X:){0,3}X)?|(?>X:){4}:(?>(?>X:){0,2}X)?|(?>X:){5}:(?>X:)?X?|(?>X:){6}:X?|(?>X:){7}(?>X|:)";
		ipv6AddressRegex = ipv6AddressRegex.replaceAll("X", wordRegex);
		// case 0: :(?>(?>:X){1,3}:0{1,4}|:0{1,4}|):5EFE:X:X
		// case 1: X:(?>:0{1,4}|:(?>X:){1,2}0{1,4}|):5EFE:X:X
		// case 2: X:X:(?>:0{1,4}|:(?>X:)?0{1,4}|):5EFE:X:X
		// case 3 and 4: X:X:X:(?>X:0{1,4}|:0{1,4}|X:|):5EFE:X:X
		// case 5: (?>X:){4}0{1,4}:5EFE:(?>X:X|:X?|X::)
		String ipv6ISATAPAddressRegex = ":(?>(?>:X){1,3}:0{1,4}|:0{1,4}|):5EFE:X:X|X:(?>:0{1,4}|:(?>X:){1,2}0{1,4}|):5EFE:X:X|X:X:(?>:0{1,4}|:(?>X:)?0{1,4}|):5EFE:X:X|X:X:X:(?>X:0{1,4}|:0{1,4}|X:|):5EFE:X:X|(?>X:){4}0{1,4}:5EFE:(?>X:X|:X?|X::)";
		ipv6ISATAPAddressRegex = ipv6ISATAPAddressRegex.replaceAll("X", wordRegex);
		ipv6Pattern = Pattern.compile(ipv6AddressRegex, Pattern.CASE_INSENSITIVE);
		ipv6PatternWithPercentScopeID = Pattern.compile("(?>" + ipv6AddressRegex + ")" + percentScopeIDRegex, Pattern.CASE_INSENSITIVE);
		ipv6ISATAPPattern = Pattern.compile("(?>" + ipv6ISATAPAddressRegex + ")" + percentScopeIDRegex, Pattern.CASE_INSENSITIVE);
	}
	
	public enum AddressType {
		OTHER, IPv4, IPv6
	}

	/**
	 * Tries to determine the address type of the given address.
	 * 
	 * REDFLAG: IPv6 percent scope ID's could cause problems with URI's.
	 * Should not be exploitable as we don't do anything important with 
	 * URI's with hosts in anyway. In particular we MUST NOT do anything 
	 * with hosts from URI's from untrusted sources e.g. content filter.
	 * But then that would be completely stupid, so we don't.
	 * 
	 * @param address
	 *            The address to determine the type of
	 * @return {@link AddressType#OTHER} if <code>address</code> is a
	 *         hostname, {@link AddressType#IPv4} or {@link AddressType#IPv6}
	 *         otherwise
	 */
	public static AddressType getAddressType(String address) {
		return AddressIdentifier.getAddressType(address,true);
	}

	/**
	 * Tries to determine the address type of the given address.
	 * 
	 * @param address
	 *            The address to determine the type of
	 * @param allowIPv6PercentScopeID
	 *            If true, match %<scope-id> suffixed IPv6 IP addresses
	 * @return {@link AddressType#OTHER} if <code>address</code> is a
	 *         hostname, {@link AddressType#IPv4} or {@link AddressType#IPv6}
	 *         otherwise
	 */
	public static AddressType getAddressType(String address, boolean allowIPv6PercentScopeID) {
		if (ipv4Pattern.matcher(address).matches()) {
			return AddressType.IPv4;
		}else if ((allowIPv6PercentScopeID ? ipv6PatternWithPercentScopeID : ipv6Pattern).matcher(address).matches()) {
			return AddressType.IPv6;
		}
		return AddressType.OTHER;
	}

	/**
	 * @see <a href="http://www.ietf.org/rfc/rfc4214.txt">rfc4214</a>
	 */
	public static boolean isAnISATAPIPv6Address(String address) {
		return ipv6ISATAPPattern.matcher(address).matches();
	}
}
