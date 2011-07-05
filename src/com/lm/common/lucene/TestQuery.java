package com.lm.common.lucene;

import java.io.IOException;
import java.util.Iterator;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

public class TestQuery {
	public static void main(String[] args) throws IOException, ParseException {
		Hits hits = null;
		String queryString = "中华";
		Query query = null;
		IndexSearcher searcher = new IndexSearcher("c:\\index");

		Analyzer analyzer = new StandardAnalyzer();
		try {
			QueryParser qp = new QueryParser("body", analyzer);
			query = qp.parse(queryString);
		} catch (ParseException e) {
		}
		if (searcher != null) {
			hits = searcher.search(query);
			if (hits.length() > 0) {
				System.out.println("找到:" + hits.length() + " 个结果!");
			}
			for (Iterator iterator = hits.iterator(); iterator.hasNext();) {
				Hit doc = (Hit) iterator.next();
				System.out.println(doc.getDocument().getField("body").stringValue());
			}
		}
	}

}