package com.everingthing.utils;

import com.everingthing.network.HttpServerTransport;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.file.*;

/**
 * 文件操作工具类
 *
 * @author baofei
 * @date 2017/12/12
 */
public class WriteFileUtil {

	private static final FileSystem ACTUAL_DEFAULT = FileSystems.getDefault();

	static volatile FileSystem DEFAULT = ACTUAL_DEFAULT;

	private void writePortsFile() {
		Path path = DEFAULT.getPath("E:\\logs").normalize().resolve("http.ports.tmp");

		try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))){
			HttpServerTransport address1 = new HttpServerTransport(InetAddress.getByName("127.0.0.1"),8080);
			HttpServerTransport address2 = new HttpServerTransport(InetAddress.getByName("::1"),8080);
			HttpServerTransport[] addresss = new HttpServerTransport[]{address1,address2};
			for (HttpServerTransport httpServerTransport : addresss) {
				InetAddress inetAddress = InetAddress.getByName("");
				if (inetAddress instanceof Inet6Address && inetAddress.isLinkLocalAddress()) {
					continue;
				}
				writer.write(HttpServerTransport.format(new InetSocketAddress(inetAddress, httpServerTransport.getPort())));
			}
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		
		Path portsFile = DEFAULT.getPath("E:\\logs").normalize().resolve("http.ports");

		try {
			Files.move(path, portsFile, StandardCopyOption.ATOMIC_MOVE);
		} catch (IOException e) {
			System.out.println("Fail to rename");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		WriteFileUtil o = new WriteFileUtil();
		o.writePortsFile();
	}

}
