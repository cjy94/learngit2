// package com.chenjunyi.scroll;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStreamWriter;
//import java.nio.file.DirectoryStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//
//
//
// public class Scroll7
// {
//   public static void main(String[] args) {
//     Scroll7 scroll = new Scroll7();
//     String cluster = null;
//     String index = null;
//     for (String arg : args) {
//       if (arg.startsWith("-cluster=", 0)) {
//         cluster = arg.split("=")[1];
//       } else if (arg.startsWith("-index=", 0)) {
//         index = arg.split("=")[1];
//       }
//     }
//
//     if (cluster == null || index == null) {
//       System.out.println("集群名称或者索引名称为空。请输入集群和索引名称，例如：-cluster=\"es\" -index=\"index\"");
//       System.exit(0);
//     }
//     System.out.println("-cluster=" + cluster + ", index=" + index);
//     scroll.ReadStoreField(cluster, index);
//     System.out.println("dump数据结束。。。");
//   }
//
//
//
//   public void ReadStoreField(String cluster, String index) {
//     Path indicesPath = null;
//     String data = null;
//     for (int i = 1; i <= 12; i++) {
//       if (i <= 9) {
//
//         data = "/data0" + i;
//       } else {
//
//         data = "/data" + i;
//       }
//
//
//       indicesPath = (new File(data + "/" + cluster + "/nodes/0/indices/")).toPath();
//       try {
//         DirectoryStream<Path> pathDirectoryStream = Files.newDirectoryStream(indicesPath);
//         for (Path indexPath : pathDirectoryStream) {
//           String uuid = indexPath.getFileName().toString();
//           if ("_all".equals(index)) {
//             DirectoryStream<Path> indexStream = Files.newDirectoryStream(indexPath);
//             for (Path shardPath : indexStream) {
//               String shardFileName = shardPath.getFileName().toString();
//               if (!"_state".equals(shardFileName)) {
//                 Path shardIndexPath = shardPath.resolve("index");
//                 System.out.println(shardIndexPath.getFileName().toString());
//                 FSDirectory fSDirectory = FSDirectory.open(shardIndexPath);
//                 DirectoryReader directoryReader = DirectoryReader.open((Directory)fSDirectory);
//                 int total = directoryReader.numDocs();
//                 System.out.println(uuid + "目录下的文档数量是： " + total);
//                 if (total > 0) {
//                   String dump = data + "/dump/" + uuid + "/" + shardFileName + "/datas.json";
//                   File file = new File(dump);
//                   FileOutputStream fos = null;
//                   OutputStreamWriter osw = null;
//                   BufferedWriter out = null;
//                   if (!file.exists()) {
//                     String parent = file.getParent();
//                     File fiel1 = new File(parent);
//                     fiel1.mkdirs();
//                     fos = new FileOutputStream(file, true);
//                     osw = new OutputStreamWriter(fos, "UTF-8");
//                     out = new BufferedWriter(osw);
//                   }
//                   else {
//
//                     fos = new FileOutputStream(file);
//                     osw = new OutputStreamWriter(fos, "UTF-8");
//                     out = new BufferedWriter(osw);
//                   }
//                   for (int j = 0; j < total; j++) {
//                     Document document = directoryReader.document(j);
//                     IndexableField source = document.getField("_source");
//                     if (source != null) {
//                       BytesRef bytesRef = source.binaryValue();
//                       if (bytesRef != null) {
//                         String string1 = bytesRef.utf8ToString();
//                         out.write(string1);
//                         out.write(System.lineSeparator());
//                       }
//                     }
//                   }
//                   out.close();
//                   osw.close();
//                   fos.close();
//                 }
//               }
//             }
//
//
//             continue;
//           }
//
//
//           if (index.equals(uuid)) {
//             DirectoryStream<Path> indexStream = Files.newDirectoryStream(indexPath);
//             for (Path shardPath : indexStream) {
//               String shardFileName = shardPath.getFileName().toString();
//               if (!"_state".equals(shardFileName)) {
//                 Path shardIndexPath = shardPath.resolve("index");
//                 FSDirectory fSDirectory = FSDirectory.open(shardIndexPath);
//                 DirectoryReader directoryReader = DirectoryReader.open((Directory)fSDirectory);
//                 int total = directoryReader.numDocs();
//                 System.out.println(uuid + "目录下的文档数量是： " + total);
//                 if (total > 0) {
//                   String dump = data + "/dump/" + uuid + "/" + shardFileName + "/datas.json";
//                   File file = new File(dump);
//                   FileOutputStream fos = null;
//                   OutputStreamWriter osw = null;
//                   BufferedWriter out = null;
//                   if (!file.exists()) {
//                     String parent = file.getParent();
//                     File fiel1 = new File(parent);
//                     fiel1.mkdirs();
//                     fos = new FileOutputStream(file, true);
//                     osw = new OutputStreamWriter(fos, "UTF-8");
//                     out = new BufferedWriter(osw);
//                   }
//                   else {
//
//                     fos = new FileOutputStream(file);
//                     osw = new OutputStreamWriter(fos, "UTF-8");
//                     out = new BufferedWriter(osw);
//                   }
//                   for (int j = 0; j < total; j++) {
//                     Document document = directoryReader.document(j);
//                     IndexableField source = document.getField("_source");
//                     if (source != null) {
//                       BytesRef bytesRef = source.binaryValue();
//                       if (bytesRef != null) {
//                         String string1 = bytesRef.utf8ToString();
//                         out.write(string1);
//                         out.write(System.lineSeparator());
//                       }
//                     }
//                   }
//                   out.close();
//                   osw.close();
//                   fos.close();
//                 }
//
//               }
//
//             }
//
//           }
//
//         }
//
//       }
//       catch (Exception e) {
//         e.printStackTrace();
//       }
//     }
//   }
// }
//
//
///* Location:              C:\Users\chenjunyi\Desktop\ck\scroll-7.7.0.jar!\com\scroll\Scroll7.class
// * Java compiler version: 8 (52.0)
// * JD-Core Version:       1.1.3
// */