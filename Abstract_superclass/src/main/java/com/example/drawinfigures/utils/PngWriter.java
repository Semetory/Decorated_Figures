package com.example.drawinfigures.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

public class PngWriter {

    public static void write(OutputStream os, int width, int height, byte[] rgba) throws IOException {
        //PNG сигнатура
        os.write(new byte[]{
                (byte) 137, 80, 78, 71, 13, 10, 26, 10
        });

        writeIHDR(os, width, height);
        writeIDAT(os, width, height, rgba);
        writeIEND(os);
    }

    private static void writeIHDR(OutputStream os, int w, int h) throws IOException {
        byte[] data = new byte[13];
        writeInt(data, 0, w);
        writeInt(data, 4, h);
        data[8] = 8;     //bit depth
        data[9] = 6;     //color type RGBA
        data[10] = 0;    //compression
        data[11] = 0;    //filter
        data[12] = 0;    //interlace

        writeChunk(os, "IHDR", data);
    }

    private static void writeIDAT(OutputStream os, int w, int h, byte[] rgba) throws IOException {
        // Каждая строка PNG должна начинаться с байта фильтра
        byte[] raw = new byte[h * (w * 4 + 1)];
        int src = 0;
        int dst = 0;

        for (int y = 0; y < h; y++) {
            raw[dst++] = 0; // filter byte (none)
            System.arraycopy(rgba, src, raw, dst, w * 4);
            src += w * 4;
            dst += w * 4;
        }

        //Сжимаем через Deflater (zlib)
        Deflater deflater = new Deflater(6);
        deflater.setInput(raw);
        deflater.finish();

        byte[] compressed = new byte[raw.length + 100];
        int len = deflater.deflate(compressed);

        byte[] idat = new byte[len];
        System.arraycopy(compressed, 0, idat, 0, len);

        writeChunk(os, "IDAT", idat);
    }

    private static void writeIEND(OutputStream os) throws IOException {
        writeChunk(os, "IEND", new byte[0]);
    }

    private static void writeChunk(OutputStream os, String type, byte[] data) throws IOException {
        writeInt(os, data.length);

        byte[] typeBytes = type.getBytes("ASCII");
        os.write(typeBytes);
        os.write(data);

        CRC32 crc = new CRC32();
        crc.update(typeBytes);
        crc.update(data);

        writeInt(os, (int) crc.getValue());
    }

    private static void writeInt(OutputStream os, int v) throws IOException {
        os.write((v >>> 24) & 0xFF);
        os.write((v >>> 16) & 0xFF);
        os.write((v >>> 8) & 0xFF);
        os.write(v & 0xFF);
    }

    private static void writeInt(byte[] arr, int pos, int v) {
        arr[pos] = (byte) ((v >>> 24) & 0xFF);
        arr[pos + 1] = (byte) ((v >>> 16) & 0xFF);
        arr[pos + 2] = (byte) ((v >>> 8) & 0xFF);
        arr[pos + 3] = (byte) (v & 0xFF);
    }
}

