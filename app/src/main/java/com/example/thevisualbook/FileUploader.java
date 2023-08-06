package com.example.thevisualbook;

class FileUploader {
   public String name;
   public String uri;

   public String getName() {
      return name;
   }

   public String getUri() {
      return uri;
   }

   public FileUploader(String name, String uri) {
      this.name = name;
      this.uri = uri;
   }

   public FileUploader(){

   }
}
