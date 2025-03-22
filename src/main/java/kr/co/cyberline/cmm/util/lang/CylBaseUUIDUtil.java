package kr.co.cyberline.cmm.util.lang;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CylBaseUUIDUtil implements Serializable {
    private final long mostSigBits;

    private final long leastSigBits;

    private transient int version = -1;

    private transient int variant = -1;

    private volatile transient long timestamp = -1L;

    private transient int sequence = -1;

    private transient long node = -1L;

    private transient int hashCode = -1;

    private static volatile SecureRandom numberGenerator = null;

    private CylBaseUUIDUtil(byte[] data) {
        long msb = 0L;
        long lsb = 0L;
        int i;
        for (i = 0; i < 8; i++)
            msb = msb << 8L | (data[i] & 0xFF);
        for (i = 8; i < 16; i++)
            lsb = lsb << 8L | (data[i] & 0xFF);
        this.mostSigBits = msb;
        this.leastSigBits = lsb;
    }

    public CylBaseUUIDUtil(long mostSigBits, long leastSigBits) {
        this.mostSigBits = mostSigBits;
        this.leastSigBits = leastSigBits;
    }

    public static CylBaseUUIDUtil randomUUID() {
        SecureRandom ng = numberGenerator;
        if (ng == null)
            numberGenerator = ng = new SecureRandom();
        byte[] randomBytes = new byte[16];
        ng.nextBytes(randomBytes);
        randomBytes[6] = (byte)(randomBytes[6] & 0xF);
        randomBytes[6] = (byte)(randomBytes[6] | 0x40);
        randomBytes[8] = (byte)(randomBytes[8] & 0x3F);
        randomBytes[8] = (byte)(randomBytes[8] | 0x80);
        return new CylBaseUUIDUtil(randomBytes);
    }

    public static CylBaseUUIDUtil nameUUIDFromBytes(byte[] name) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException nsae) {
            throw new InternalError("SHA-256 not supported");
        }
        if (md == null)
            throw new RuntimeException("MessageDigest is null!!");
        SecureRandom ng = new SecureRandom();
        byte[] randomBytes = new byte[16];
        ng.nextBytes(randomBytes);
        md.reset();
        md.update(randomBytes);
        byte[] sha = md.digest(name);
        byte[] md5Bytes = new byte[8];
        System.arraycopy(sha, 0, md5Bytes, 0, 8);
        md5Bytes[6] = (byte)(md5Bytes[6] & 0xF);
        md5Bytes[6] = (byte)(md5Bytes[6] | 0x30);
        md5Bytes[8] = (byte)(md5Bytes[8] & 0x3F);
        md5Bytes[8] = (byte)(md5Bytes[8] | 0x80);
        return new CylBaseUUIDUtil(md5Bytes);
    }

    public static CylBaseUUIDUtil fromString(String name) {
        String[] components = name.split("-");
        if (components.length != 5)
            throw new IllegalArgumentException("Invalid UUID string: " + name);
        for (int i = 0; i < 5; i++)
            components[i] = "0x" + components[i];
        long mostSigBits = Long.decode(components[0]).longValue();
        mostSigBits <<= 16L;
        mostSigBits |= Long.decode(components[1]).longValue();
        mostSigBits <<= 16L;
        mostSigBits |= Long.decode(components[2]).longValue();
        long leastSigBits = Long.decode(components[3]).longValue();
        leastSigBits <<= 48L;
        leastSigBits |= Long.decode(components[4]).longValue();
        return new CylBaseUUIDUtil(mostSigBits, leastSigBits);
    }

    public long getLeastSignificantBits() {
        return this.leastSigBits;
    }

    public long getMostSignificantBits() {
        return this.mostSigBits;
    }

    public int version() {
        if (this.version < 0)
            this.version = (int)(this.mostSigBits >> 12L & 0xFL);
        return this.version;
    }

    public int variant() {
        if (this.variant < 0)
            if (this.leastSigBits >>> 63L == 0L) {
                this.variant = 0;
            } else if (this.leastSigBits >>> 62L == 2L) {
                this.variant = 2;
            } else {
                this.variant = (int)(this.leastSigBits >>> 61L);
            }
        return this.variant;
    }

    public long timestamp() {
        if (version() != 1)
            throw new UnsupportedOperationException("Not a time-based UUID");
        long result = this.timestamp;
        if (result < 0L) {
            result = (this.mostSigBits & 0xFFFL) << 48L;
            result |= (this.mostSigBits >> 16L & 0xFFFFL) << 32L;
            result |= this.mostSigBits >>> 32L;
            this.timestamp = result;
        }
        return result;
    }

    public int clockSequence() {
        if (version() != 1)
            throw new UnsupportedOperationException("Not a time-based UUID");
        if (this.sequence < 0)
            this.sequence = (int)((this.leastSigBits & 0x3FFF000000000000L) >>> 48L);
        return this.sequence;
    }

    public long node() {
        if (version() != 1)
            throw new UnsupportedOperationException("Not a time-based UUID");
        if (this.node < 0L)
            this.node = this.leastSigBits & 0xFFFFFFFFFFFFL;
        return this.node;
    }

    public String toString() {
        return digits(this.mostSigBits >> 32L, 8) + "-" +
                digits(this.mostSigBits >> 16L, 4) + "-" + digits(this.mostSigBits, 4) + "-" +
                digits(this.leastSigBits >> 48L, 4) + "-" + digits(this.leastSigBits, 12);
    }

    private static String digits(long val, int digits) {
        long hi = 1L << digits * 4;
        return Long.toHexString(hi | val & hi - 1L).substring(1);
    }

    public int hashCode() {
        if (this.hashCode == -1)
            this.hashCode = (int)(this.mostSigBits >> 32L ^ this.mostSigBits ^ this.leastSigBits >> 32L ^ this.leastSigBits);
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof CylBaseUUIDUtil))
            return false;
        if (((CylBaseUUIDUtil)obj).variant() != variant())
            return false;
        CylBaseUUIDUtil id = (CylBaseUUIDUtil)obj;
        return (this.mostSigBits == id.mostSigBits && this.leastSigBits == id.leastSigBits);
    }

    public int compareTo(CylBaseUUIDUtil val) {
        return (this.mostSigBits < val.mostSigBits) ? -1 : ((this.mostSigBits > val.mostSigBits) ? 1 : ((this.leastSigBits < val.leastSigBits) ? -1 : ((this.leastSigBits > val.leastSigBits) ? 1 : 0)));
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.version = -1;
        this.variant = -1;
        this.timestamp = -1L;
        this.sequence = -1;
        this.node = -1L;
        this.hashCode = -1;
    }
}
