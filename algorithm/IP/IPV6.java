//package com.chenjunyi.IP;
//
//import org.apache.lucene.document.BinaryPoint;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.index.*;
//import org.apache.lucene.search.*;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//
//import javax.print.Doc;
//import java.io.File;
//import java.io.IOException;
//import java.net.Inet6Address;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.Arrays;
//
//public class IPV6 {
//    public static void main(String[] args) {
//        try {
//            String ip1 = "a139:f0d3:9a3d:362:1028:39b6:d29b:b500";
//            // 十六进制形式： a1 39 f0 d3 9a 3d 3 62 10 28 39 b6 d2 9b b5 00
//            // a1: 10*16^1+1*16^0 = 161
//            String ip2 = "e1b:f98:8689:ffee:70f0:de47:eedb:b6de";
//            // 十六进制形式：e 1b f 98 86 89 ff ee 70 f0 de 47 ee db b6 de
//            // e: 14
//            String ip3 = "e3fc:a66b:f79e:94bc:e77d:83da:195a:8e61";
//            // 十六进制形式：e3 fc a6 6b f7 9e 94 bc e7 7d 83 da 19 5a 8e 61
//            // e3:  227
//            // 排序: ip2 ip1 ip3
//            byte[] bytes1 = InetAddresses.ipStringToBytes(ip1);
//            System.out.println(Arrays.toString(bytes1));
//
//            byte[] bytes2 = InetAddresses.ipStringToBytes(ip2);
//            System.out.println(Arrays.toString(bytes2));
//
//            byte[] bytes3 = InetAddresses.ipStringToBytes(ip3);
//            System.out.println(Arrays.toString(bytes3));
//
//            Directory dir = FSDirectory.open(new File("D:\\lucene\\lucene_20").toPath());
//            IndexWriter iw = new IndexWriter(dir, new IndexWriterConfig());
//
//            Document doc = new Document();
//            doc.add(new BinaryPoint("hostname", bytes1));
//
//            iw.addDocument(doc);
//            doc.clear();
//            doc.add(new BinaryPoint("hostname", bytes2));
//            iw.addDocument(doc);
//            doc.clear();
//            doc.add(new BinaryPoint("hostname", bytes3));
//            iw.addDocument(doc);
//            doc.clear();
//            iw.close();
//
//            IndexReader r = DirectoryReader.open(dir);
//            IndexSearcher search = new IndexSearcher(r);
//
//            Query q = new PointRangeQuery("hostname", bytes1, bytes3, 1) {
//                @Override
//                protected String toString(int i, byte[] bytes) {
//
//                    return new String(bytes);
//                }
//            };
//            TopDocs topDocs = search.search(q, 100);
//
//            for (ScoreDoc docs : topDocs.scoreDocs) {
//                System.out.println(docs.doc);
//            }
//            System.out.println("totalHits: " + topDocs.totalHits);
//
//
//            q = new PointRangeQuery("hostname", bytes1, bytes2, 1) {
//                @Override
//                protected String toString(int dimension, byte[] value) {
//                    return new String(value);
//                }
//            };
//            topDocs = search.search(q, 100);
//            for (ScoreDoc docs : topDocs.scoreDocs) {
//                System.out.println(docs.doc);
//            }
//            System.out.println("totalHits: " + topDocs.totalHits);
//
//            q = new PointRangeQuery("hostname", bytes2, bytes3, 1) {
//                @Override
//                protected String toString(int dimension, byte[] value) {
//                    return new String(value);
//                }
//            };
//            topDocs = search.search(q, 100);
//            for (ScoreDoc docs : topDocs.scoreDocs) {
//                System.out.println(docs.doc);
//            }
//            System.out.println("totalHits: " + topDocs.totalHits);
//
//            q = new PointRangeQuery("hostname", bytes2, bytes2, 1) {
//                @Override
//                protected String toString(int dimension, byte[] value) {
//                    return new String(value);
//                }
//            };
//            topDocs = search.search(q, 100);
//            for (ScoreDoc docs : topDocs.scoreDocs) {
//                System.out.println(docs.doc);
//            }
//            System.out.println("totalHits: " + topDocs.totalHits);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//}
