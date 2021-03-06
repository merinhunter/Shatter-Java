package cipher;

import java.nio.ByteBuffer;

import common.Bytes;
import rsa.SecureSignature;

public class EncFile {
	private FalseHeader false_header;
	private EncFileHeader header;
	private byte[] data;

	public EncFile(byte[] data, byte[] iv) {
		this.data = data;
		this.header = new EncFileHeader(iv);
		this.false_header = new FalseHeader();
	}

	public EncFile(EncFileHeader header, byte[] data) {
		this.data = data;
		this.header = header;
		this.false_header = new FalseHeader();
	}

	public byte[] getID() {
		return this.false_header.getID();
	}

	public void setID(byte[] id) {
		this.false_header.setID(id);
	}

	public byte[] getIv() {
		return this.header.getIV();
	}

	public void setIv(byte[] iv) {
		this.header.setIV(iv);
	}

	public SecureSignature getSignature() {
		return this.header.getIV_signature();
	}

	public void setSignature(SecureSignature signature) {
		this.header.setIV_signature(signature);
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public byte[] toBytes() {
		return Bytes.concat(this.header.toBytes(), this.data);
	}

	public static EncFile fromBytes(byte[] src) {
		ByteBuffer buffer = ByteBuffer.wrap(src);
		byte[] data = new byte[src.length - EncFileHeader.BYTES];

		EncFileHeader header = EncFileHeader.fromBytes(buffer);
		buffer.get(data);

		return new EncFile(header, data);
	}
}
