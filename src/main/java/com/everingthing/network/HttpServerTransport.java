package com.everingthing.network;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Objects;

/**
 * 接口
 *
 * @author baofei
 * @date 2017/12/13
 */
public final class HttpServerTransport {

	public static InetAddress META_ADDRESS;

	private static int IPV6_PART_COUNT = 8;

	static{
		try {
			META_ADDRESS = InetAddress.getByName("0.0.0.0");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private InetSocketAddress address;

	public HttpServerTransport(InetAddress address, int port) {
		this(new InetSocketAddress(address, port));
	}

	public HttpServerTransport(InetSocketAddress address) {
		if (address == null) {
			throw new IllegalArgumentException("参数【InetSocketAddress】不能为空");
		}
		if (address.getAddress() == null) {
			throw new IllegalArgumentException("方法【address.getAddress()】不能为空");
		}
		this.address = address;
	}

	public HttpServerTransport(InputStream in){
		this(in, null);
	}

	public HttpServerTransport(InputStream in, String hostString) {
//		final short i = in.r
	}

	public void WriteTo(OutputStream out) {

	}

	public String getAddress() {
		return format(address.getAddress(),-1);
	}

	public static String format(InetAddress address, int port) {
		Objects.requireNonNull(address);

		StringBuilder builder = new StringBuilder();

		if (port != -1 && address instanceof Inet6Address) {
			builder.append("[" + toAddrString(address) + "]");
		} else {
			builder.append(toAddrString(address));
		}

		if (port != -1) {
			builder.append(':');
			builder.append(port);
		}

		return builder.toString();
	}

	private static String toAddrString(InetAddress ip) {
		byte[] bytes = ip.getAddress();
		int[] hextets = new int[IPV6_PART_COUNT];
		for (int i = 0; i < hextets.length; i++) {
			hextets[i] =  (bytes[2 * i] & 255) << 8 | bytes[2 * i + 1] & 255;
		}
		compressLongestRunOfZeroes(hextets);
		return hextetsToIPv6String(hextets);
	}

	private static String hextetsToIPv6String(int[] hextets) {
    /*
     * While scanning the array, handle these state transitions:
     *   start->num => "num"     start->gap => "::"
     *   num->num   => ":num"    num->gap   => "::"
     *   gap->num   => "num"     gap->gap   => ""
     */
		StringBuilder buf = new StringBuilder(39);
		boolean lastWasNumber = false;
		for (int i = 0; i < hextets.length; i++) {
			boolean thisIsNumber = hextets[i] >= 0;
			if (thisIsNumber) {
				if (lastWasNumber) {
					buf.append(':');
				}
				buf.append(Integer.toHexString(hextets[i]));
			} else {
				if (i == 0 || lastWasNumber) {
					buf.append("::");
				}
			}
			lastWasNumber = thisIsNumber;
		}
		return buf.toString();
	}

	private static void compressLongestRunOfZeroes(int[] hextets) {
		int bestRunStart = -1;
		int bestRunLength = -1;
		int runStart = -1;
		for (int i = 0; i < hextets.length + 1; i++) {
			if (i < hextets.length && hextets[i] == 0) {
				if (runStart < 0) {
					runStart = i;
				}
			} else if (runStart >= 0) {
				int runLength = i - runStart;
				if (runLength > bestRunLength) {
					bestRunStart = runStart;
					bestRunLength = runLength;
				}
				runStart = -1;
			}
		}
		if (bestRunLength >= 2) {
			Arrays.fill(hextets, bestRunStart, bestRunStart + bestRunLength, -1);
		}
	}

	public int getPort() {
		return address.getPort();
	}

	public InetSocketAddress address() {
		return this.address;
	}

	public static String format(InetSocketAddress inetSocketAddress) {
		return format(inetSocketAddress.getAddress(), inetSocketAddress.getPort());
	}
}
