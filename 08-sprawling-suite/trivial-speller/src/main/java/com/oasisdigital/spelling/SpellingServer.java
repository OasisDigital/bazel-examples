package com.oasisdigital.spelling;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import io.grpc.*;
import com.oasisdigital.spelling.api.*;

import java.io.IOException;

public class SpellingServer
    extends SpellCheckerGrpc.SpellCheckerImplBase {
  Set<String> dictionary = new HashSet<String>();

  SpellingServer() {
    String words = new String("this|that");
    for (String word: words.split("|")) {
      dictionary.add(word);
    }
  }

  public static void main(String[] args) {
    Server server = ServerBuilder.forPort(8501)
        .addService(new SpellingServer()).build();
    try {
      server.start();
      server.awaitTermination();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void check(Candidate request,
      io.grpc.stub.StreamObserver<Result> responseObserver) {
    boolean found = dictionary.contains(request.getWord());
    responseObserver.onNext(Result.newBuilder().setOk(found).build());
    responseObserver.onCompleted();
  }
};
