package de.haevn.utils.io.file;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZipUtils {
    public static ZipUtils open() {
        return new ZipUtils();
    }

    private ZipUtils() {
    }

    private String password = "";
    private CompressionLevel compressionLevel = CompressionLevel.HIGHER;
    private final FileBuilder fileBuilder = new FileBuilder();

    public ZipUtils withPassword(final String password) {
        this.password = password;
        return this;
    }

    public ZipUtils setCompressionLevel(final CompressionLevel compressionLevel) {
        this.compressionLevel = compressionLevel;
        return this;
    }

    public FileBuilder fileBuilder() {
        return fileBuilder;
    }


    public void zip(final File output) throws IOException {
        zip(output, fileBuilder.files);
    }

    public void zip(final String output, final File... files) throws IOException {
        zip(new File(output), List.of(files));
    }

    public void zipDirectory(final File output, final File directory) throws IOException {

        FileIO.createFileIfNotExists(output);

        final ZipParameters zipParameters = new ZipParameters();
        zipParameters.setCompressionLevel(compressionLevel);
        try (final ZipFile zipFile = new ZipFile(output)) {
            if (password != null && !password.isEmpty()) {
                zipParameters.setEncryptionMethod(EncryptionMethod.AES);
                zipParameters.setEncryptFiles(true);
                zipFile.setPassword(password.toCharArray());
            }
            zipFile.addFolder(directory, zipParameters);
        } catch (Exception e) {
            output.delete();
            throw new IOException(e);
        }
    }

    public void zip(final File output, final List<File> files) throws IOException {
        if (files.isEmpty()) {
            return;
        }

        FileIO.createFileIfNotExists(output);

        final ZipParameters zipParameters = new ZipParameters();
        zipParameters.setCompressionLevel(compressionLevel);

        try (final ZipFile zipFile = new ZipFile(output)) {
            if (password != null && !password.isEmpty()) {
                zipParameters.setEncryptionMethod(EncryptionMethod.AES);
                zipParameters.setEncryptFiles(true);
                zipFile.setPassword(password.toCharArray());
            }
            zipFile.addFiles(files, zipParameters);
        } catch (Exception e) {
            output.delete();
            throw new IOException(e);
        }
    }


    public void unzip(final File inputZipFile, final File outputDirectory) throws IOException {

        try (final ZipFile zipFile = new ZipFile(inputZipFile)) {
            if (password != null && !password.isEmpty()) {
                zipFile.setPassword(password.toCharArray());
            }
            zipFile.extractAll(outputDirectory.getAbsolutePath());
        } catch (Exception e) {
            throw new IOException(e);
        }
    }


    public class FileBuilder {
        private final List<File> files = new ArrayList<>();

        public FileBuilder add(final File file) {
            files.add(file);
            return this;
        }

        public ZipUtils zipUtils() {
            return ZipUtils.this;
        }
    }
}
