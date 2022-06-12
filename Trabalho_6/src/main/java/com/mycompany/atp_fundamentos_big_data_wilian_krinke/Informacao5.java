/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.atp_fundamentos_big_data_wilian_krinke;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author wilian.krinke
 */
public class Informacao5 {  
    
    public static class Implementacao5MapperAtp extends Mapper<Object, Text, Text, IntWritable>{
        
        @Override
        public void map(Object id, Text valor, Context context) throws IOException, InterruptedException{
            try{
                String linha = valor.toString();
                String[] campos = linha.split(";");

                IntWritable one = new IntWritable(1);

                if(campos.length == 10 && campos[1].equals("2016")){
                    /*Mercadoria com maior quantidade de transações financeiras em 2016*/;                
                   Text mercadoria = new Text(campos[3] + " | ");
                   context.write(mercadoria, one);  ;
                }   
            }catch(Exception e){
                System.out.println(e);
            }
                 
        }
    }
    
    public static class Implementacao5ReducerAtp extends Reducer<Text, IntWritable, Text, IntWritable>{
    
        @Override
        public void reduce(Text chave, Iterable<IntWritable> valores, Context context) throws IOException, InterruptedException{
            try{
                int soma = 0;
                IntWritable resultado = new IntWritable();

                for(IntWritable valor : valores){
                    soma += valor.get();                
                }

                resultado.set(soma);
                context.write(chave, resultado);
            }catch(Exception e){
                System.out.println(e);
            }
            
        }
    
    }
    
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException{
       
        String entrada_arquivo = "/home2/ead2022/SEM1/wilian.krinke/Documents/base_100_mil.csv";
        String saida_pasta = "/home2/ead2022/SEM1/wilian.krinke/Documents/local/tarefa5";
        
        if(args.length == 2){
            entrada_arquivo = args[0];
            saida_pasta = args[1];
        }
        
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Implementação5.txt");
        
        job.setJarByClass(Informacao5.class);
        job.setMapperClass(Implementacao5MapperAtp.class);
        job.setReducerClass(Implementacao5ReducerAtp.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job, new Path(entrada_arquivo));
        FileOutputFormat.setOutputPath(job, new Path(saida_pasta));
        
        job.waitForCompletion(true);
    }
}
