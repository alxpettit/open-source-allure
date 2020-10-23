package net.Shad0w.Allure.Utils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;
import net.minecraft.nbt.PositionTracker;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.TagReader;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class AllureStringTag implements Tag {
   public static final TagReader READER = new TagReader() {
      public AllureStringTag read(DataInput dataInput, int i, PositionTracker positionTracker) throws IOException {
         positionTracker.method_10623(288L);
         String string = dataInput.readUTF();
         positionTracker.method_10623((long)(16 * string.length()));
         return AllureStringTag.of(string);
      }

      public String method_23259() {
         return "STRING";
      }

      public String method_23261() {
         return "TAG_String";
      }

      public boolean method_23263() {
         return true;
      }
   };
   private static final AllureStringTag EMPTY = new AllureStringTag("");
   private final String value;

   public AllureStringTag(String value) {
      Objects.requireNonNull(value, "Null string not allowed");
      this.value = value;
   }

   public static AllureStringTag of(String value) {
      return value.isEmpty() ? EMPTY : new AllureStringTag(value);
   }

   public void method_10713(DataOutput output) throws IOException {
      output.writeUTF(this.value);
   }

   public byte method_10711() {
      return 8;
   }

   public TagReader method_23258() {
      return READER;
   }

   public String toString() {
      return escape(this.value);
   }

   public AllureStringTag copy() {
      return this;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else {
         return o instanceof AllureStringTag && Objects.equals(this.value, ((AllureStringTag)o).value);
      }
   }

   public int hashCode() {
      return this.value.hashCode();
   }

   public String method_10714() {
      return this.value;
   }

   public Text method_10710(String indent, int depth) {
      String string = escape(this.value);
      String string2 = string.substring(0, 1);
      Text text = (new LiteralText(string.substring(1, string.length() - 1))).method_27692(field_11594);
      return (new LiteralText(string2)).method_10852(text).method_27693(string2);
   }

   public static String escape(String value) {
      StringBuilder stringBuilder = new StringBuilder(" ");
      char c = 0;

      for(int i = 0; i < value.length(); ++i) {
         char d = value.charAt(i);
         if (d == '\\') {
            stringBuilder.append('\\');
         } else if (d == '"' || d == '\'') {
            if (c == 0) {
               c = (char)(d == '"' ? 39 : 34);
            }

            if (c == d) {
               stringBuilder.append('\\');
            }
         }

         stringBuilder.append(d);
      }

      if (c == 0) {
         c = '"';
      }

      stringBuilder.setCharAt(0, c);
      stringBuilder.append(c);
      return stringBuilder.toString();
   }
}
