package com.lm.common.lucene;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

/**
 * author lighter date 2006-8-7
 */
public class TextFileIndexer {
	public static void main(String[] args) throws Exception {
		/* ָ��Ҫ�����ļ��е�λ��,������C�̵�S�ļ����� */
		File fileDir = new File("c:\\s");

		/* ����������ļ���λ�� */
		File indexDir = new File("c:\\index");
		Analyzer luceneAnalyzer = new StandardAnalyzer();
		IndexWriter indexWriter = new IndexWriter(indexDir, luceneAnalyzer,
				true);
		File[] textFiles = fileDir.listFiles();
		long startTime = new Date().getTime();
		
		//����document������ȥ
		for (int i = 0; i < textFiles.length; i++) {
			if (textFiles[i].isFile()
					&& textFiles[i].getName().endsWith(".txt")) {
				System.out.println("File " + textFiles[i].getCanonicalPath()
						+ "���ڱ�����....");
				String temp = FileReaderAll(textFiles[i].getCanonicalPath(),
						"GBK");
				System.out.println(temp);
				Document document = new Document();
				Field FieldPath = new Field("path", textFiles[i].getPath(),
						Field.Store.YES, Field.Index.NO);
				Field FieldBody = new Field("body", temp, Field.Store.YES,
						Field.Index.TOKENIZED,
						Field.TermVector.WITH_POSITIONS_OFFSETS);
				document.add(FieldPath);
				document.add(FieldBody);
				indexWriter.addDocument(document);
			}
		}
		//optimize()�����Ƕ����������Ż�
		indexWriter.optimize();
		indexWriter.close();
		
		//����һ��������ʱ��
		long endTime = new Date().getTime();
		System.out
				.println("�⻨����"
						+ (endTime - startTime)
						+ " ���������ĵ����ӵ���������ȥ!"
						+ fileDir.getPath());
	}

	public static String FileReaderAll(String FileName, String charset)
			throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(FileName), charset));
		String line = new String();
		String temp = new String();
		
		while ((line = reader.readLine()) != null) {
			temp += line;
		}
		reader.close();
		return temp;
	}
}