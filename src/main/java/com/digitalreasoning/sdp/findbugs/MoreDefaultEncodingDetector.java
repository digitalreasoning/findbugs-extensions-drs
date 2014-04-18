package com.digitalreasoning.sdp.findbugs;

import java.util.HashSet;
import java.util.Set;

import edu.umd.cs.findbugs.BugAccumulator;
import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.MethodAnnotation;
import edu.umd.cs.findbugs.ba.AnnotationDatabase;
import edu.umd.cs.findbugs.ba.AnnotationEnumeration;
import edu.umd.cs.findbugs.ba.XFactory;
import edu.umd.cs.findbugs.ba.XMethod;
import edu.umd.cs.findbugs.bcel.OpcodeStackDetector;
import edu.umd.cs.findbugs.classfile.ClassDescriptor;
import edu.umd.cs.findbugs.classfile.DescriptorFactory;
import edu.umd.cs.findbugs.internalAnnotations.DottedClassName;

import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.JavaClass;

/**
 * TODO: Document this class
 */
public class MoreDefaultEncodingDetector
		extends OpcodeStackDetector
{

	private final BugAccumulator bugAccumulator;

	private final MoreDefaultEncodingAnnotationDatabase defaultEncodingAnnotationDatabase;

	/**
	 * This annotation is used to denote a method which relies on the default
	 * platform encoding.
	 */
	static class MoreDefaultEncodingAnnotation extends AnnotationEnumeration<MoreDefaultEncodingAnnotation>
	{
		public final static MoreDefaultEncodingAnnotation DEFAULT_ENCODING = new MoreDefaultEncodingAnnotation("DefaultEncoding", 1);

		private final static MoreDefaultEncodingAnnotation[] myValues = { DEFAULT_ENCODING };

		public static MoreDefaultEncodingAnnotation[] values() {
			return myValues.clone();
		}

		private MoreDefaultEncodingAnnotation(String s, int i) {
			super(s, i);
		}
	}

	/**
	 * Sets up and stores DefaultEncodingAnnotations on JCL methods.
	 */
	static class MoreDefaultEncodingAnnotationDatabase extends AnnotationDatabase<MoreDefaultEncodingAnnotation>
	{

		public MoreDefaultEncodingAnnotationDatabase() {
			this.setAddClassOnly(false);
			this.loadAuxiliaryAnnotations();
		}

		Set<ClassDescriptor> classes = new HashSet<ClassDescriptor>();
		@Override
		protected void addMethodAnnotation(@DottedClassName String cName, String mName, String mSig, boolean isStatic,
		                                   MoreDefaultEncodingAnnotation annotation) {
			super.addMethodAnnotation(cName, mName, mSig, isStatic, annotation);
			classes.add(DescriptorFactory.createClassDescriptorFromDottedClassName(cName));

		}

		@Override
		public void loadAuxiliaryAnnotations() {
			// commons-io
			// FileUtils
			addMethodAnnotation("org.apache.commons.io.FileUtils", "readFileToString", "(Ljava/io/File;)Ljava/lang/String;", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.FileUtils", "readLines", "(Ljava/io/File;)Ljava/util/List;", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.FileUtils", "lineIterator", "(Ljava/io/File;)Lorg/apache/commons/io/LineIterator;", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.FileUtils", "writeStringToFile", "(Ljava/io/File;Ljava/lang/String;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.FileUtils", "writeStringToFile", "(Ljava/io/File;Ljava/lang/String;Z)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.FileUtils", "write", "(Ljava/io/File;Ljava/lang/CharSequence;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.FileUtils", "write", "(Ljava/io/File;Ljava/lang/CharSequence;Z)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.FileUtils", "writeLines", "(Ljava/io/File;Ljava/util/Collection;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.FileUtils", "writeLines", "(Ljava/io/File;Ljava/util/Collection;Z)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.FileUtils", "writeLines", "(Ljava/io/File;Ljava/util/Collection;Ljava/lang/String;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.FileUtils", "writeLines", "(Ljava/io/File;Ljava/util/Collection;Ljava/lang/String;Z)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);

			// ReaderInputStream
			addMethodAnnotation("org.apache.commons.io.input.ReaderInputStream", "<init>", "(Ljava/io/Reader;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);

			// ReversedLinesFileReader
			addMethodAnnotation("org.apache.commons.io.input.ReversedLinesFileReader", "<init>", "(Ljava/io/File;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);

			// WriterOutputStream
			addMethodAnnotation("org.apache.commons.io.output.WriterOutputStream", "<init>", "(Ljava/io/Writer;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);

			// IOUtils
			addMethodAnnotation("org.apache.commons.io.IOUtils", "copy", "(Ljava/io/InputStream;Ljava/io/Writer;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "copy", "(Ljava/io/Reader;Ljava/io/OutputStream;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "readLines", "(Ljava/io/InputStream;)Ljava/util/List;", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "toByteArray", "(Ljava/io/Reader;)[B", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "toByteArray", "(Ljava/lang/String;)[B", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "toCharArray", "(Ljava/io/InputStream;)[C", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "toInputStream", "(Ljava/lang/CharSequence;)Ljava/io/InputStream;", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "toInputStream", "(Ljava/lang/String;)Ljava/io/InputStream;", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "toString", "(Ljava/io/InputStream;)Ljava/lang/String;", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "toString", "(Ljava/net/URI;)Ljava/lang/String;", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "toString", "(Ljava/net/URL;)Ljava/lang/String;", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "toString", "([B)Ljava/lang/String;", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "write", "([BLjava/io/Writer;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "write", "([CLjava/io/OutputStream;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "write", "(Ljava/lang/CharSequence;Ljava/io/OutputStream;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "write", "(Ljava/lang/StringBuffer;Ljava/io/OutputStream;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "write", "(Ljava/lang/String;Ljava/io/OutputStream;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.IOUtils", "writeLines", "(Ljava/util/Collection;Ljava/lang/String;Ljava/io/OutputStream;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);

			// CopyUtils
			addMethodAnnotation("org.apache.commons.io.CopyUtils", "copy", "([BLjava/io/Writer;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.CopyUtils", "copy", "(Ljava/io/InputStream;Ljava/io/Writer;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.CopyUtils", "copy", "(Ljava/io/Reader;Ljava/io/OutputStream;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.CopyUtils", "copy", "(Ljava/lang/String;Ljava/io/OutputStream;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);


			// LockableFileWriter
			addMethodAnnotation("org.apache.commons.io.output.LockableFileWriter", "<init>", "(Ljava/io/File;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.output.LockableFileWriter", "<init>", "(Ljava/io/File;Z)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.output.LockableFileWriter", "<init>", "(Ljava/io/File;Ljava/lang/String;Z)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.output.LockableFileWriter", "<init>", "(Ljava/lang/String;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.output.LockableFileWriter", "<init>", "(Ljava/lang/String;Z)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.output.LockableFileWriter", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);


			// XmlStreamReader
			addMethodAnnotation("org.apache.commons.io.input.XmlStreamReader", "<init>", "(Ljava/io/File;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.input.XmlStreamReader", "<init>", "(Ljava/io/InputStream;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.input.XmlStreamReader", "<init>", "(Ljava/io/InputStream;Z)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.input.XmlStreamReader", "<init>", "(Ljava/io/InputStream;Ljava/lang/String;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.input.XmlStreamReader", "<init>", "(Ljava/io/InputStream;Ljava/lang/String;Z)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.input.XmlStreamReader", "<init>", "(Ljava/net/URL;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);

			// XmlStreamWriter
			addMethodAnnotation("org.apache.commons.io.output.XmlStreamWriter", "<init>", "(Ljava/io/OutputStream;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
			addMethodAnnotation("org.apache.commons.io.output.XmlStreamWriter", "<init>", "(Ljava/io/File;)V", false,
			                    MoreDefaultEncodingAnnotation.DEFAULT_ENCODING);
		}

	}

	public MoreDefaultEncodingDetector(BugReporter bugReporter) {
		this.bugAccumulator = new BugAccumulator(bugReporter);
		this.defaultEncodingAnnotationDatabase = new MoreDefaultEncodingAnnotationDatabase();
	}

	@Override
	public boolean shouldVisit(JavaClass obj) {
		Set<ClassDescriptor> called = getXClass().getCalledClassDescriptors();
		for(ClassDescriptor c : defaultEncodingAnnotationDatabase.classes) {
			if (called.contains(c)) {
				return true;
			}
		}

		return false;
	}
	@Override
	public void visit(Code code) {
		super.visit(code); // make callbacks to sawOpcode for all opcodes
		bugAccumulator.reportAccumulatedBugs();

	}
	@Override
	public void sawOpcode(int seen) {
		switch (seen) {
			case INVOKEVIRTUAL:
			case INVOKESPECIAL:
			case INVOKESTATIC:
				XMethod callSeen = XFactory.createXMethod(MethodAnnotation.fromCalledMethod(this));
				MoreDefaultEncodingAnnotation annotation = defaultEncodingAnnotationDatabase.getDirectAnnotation(callSeen);
				if (annotation != null) {
					bugAccumulator.accumulateBug(new BugInstance(this, "DM_DEFAULT_ENCODING", HIGH_PRIORITY).addClassAndMethod(this)
					                                                                                        .addCalledMethod(this), this);
				}
		}
	}
}
