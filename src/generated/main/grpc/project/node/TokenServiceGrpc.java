package project.node;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.12.0)",
    comments = "Source: Token.proto")
public final class TokenServiceGrpc {

  private TokenServiceGrpc() {}

  public static final String SERVICE_NAME = "project.node.TokenService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSendTokenMethod()} instead. 
  public static final io.grpc.MethodDescriptor<project.node.Token.TokenObject,
      project.node.Token.ACK> METHOD_SEND_TOKEN = getSendTokenMethodHelper();

  private static volatile io.grpc.MethodDescriptor<project.node.Token.TokenObject,
      project.node.Token.ACK> getSendTokenMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<project.node.Token.TokenObject,
      project.node.Token.ACK> getSendTokenMethod() {
    return getSendTokenMethodHelper();
  }

  private static io.grpc.MethodDescriptor<project.node.Token.TokenObject,
      project.node.Token.ACK> getSendTokenMethodHelper() {
    io.grpc.MethodDescriptor<project.node.Token.TokenObject, project.node.Token.ACK> getSendTokenMethod;
    if ((getSendTokenMethod = TokenServiceGrpc.getSendTokenMethod) == null) {
      synchronized (TokenServiceGrpc.class) {
        if ((getSendTokenMethod = TokenServiceGrpc.getSendTokenMethod) == null) {
          TokenServiceGrpc.getSendTokenMethod = getSendTokenMethod = 
              io.grpc.MethodDescriptor.<project.node.Token.TokenObject, project.node.Token.ACK>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "project.node.TokenService", "sendToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.node.Token.TokenObject.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.node.Token.ACK.getDefaultInstance()))
                  .setSchemaDescriptor(new TokenServiceMethodDescriptorSupplier("sendToken"))
                  .build();
          }
        }
     }
     return getSendTokenMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getInsertNotificationMethod()} instead. 
  public static final io.grpc.MethodDescriptor<project.node.Token.NodeDescription,
      project.node.Token.ACK> METHOD_INSERT_NOTIFICATION = getInsertNotificationMethodHelper();

  private static volatile io.grpc.MethodDescriptor<project.node.Token.NodeDescription,
      project.node.Token.ACK> getInsertNotificationMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<project.node.Token.NodeDescription,
      project.node.Token.ACK> getInsertNotificationMethod() {
    return getInsertNotificationMethodHelper();
  }

  private static io.grpc.MethodDescriptor<project.node.Token.NodeDescription,
      project.node.Token.ACK> getInsertNotificationMethodHelper() {
    io.grpc.MethodDescriptor<project.node.Token.NodeDescription, project.node.Token.ACK> getInsertNotificationMethod;
    if ((getInsertNotificationMethod = TokenServiceGrpc.getInsertNotificationMethod) == null) {
      synchronized (TokenServiceGrpc.class) {
        if ((getInsertNotificationMethod = TokenServiceGrpc.getInsertNotificationMethod) == null) {
          TokenServiceGrpc.getInsertNotificationMethod = getInsertNotificationMethod = 
              io.grpc.MethodDescriptor.<project.node.Token.NodeDescription, project.node.Token.ACK>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "project.node.TokenService", "insertNotification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.node.Token.NodeDescription.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.node.Token.ACK.getDefaultInstance()))
                  .setSchemaDescriptor(new TokenServiceMethodDescriptorSupplier("insertNotification"))
                  .build();
          }
        }
     }
     return getInsertNotificationMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getDeleteNotificationMethod()} instead. 
  public static final io.grpc.MethodDescriptor<project.node.Token.NodeDescription,
      project.node.Token.ACK> METHOD_DELETE_NOTIFICATION = getDeleteNotificationMethodHelper();

  private static volatile io.grpc.MethodDescriptor<project.node.Token.NodeDescription,
      project.node.Token.ACK> getDeleteNotificationMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<project.node.Token.NodeDescription,
      project.node.Token.ACK> getDeleteNotificationMethod() {
    return getDeleteNotificationMethodHelper();
  }

  private static io.grpc.MethodDescriptor<project.node.Token.NodeDescription,
      project.node.Token.ACK> getDeleteNotificationMethodHelper() {
    io.grpc.MethodDescriptor<project.node.Token.NodeDescription, project.node.Token.ACK> getDeleteNotificationMethod;
    if ((getDeleteNotificationMethod = TokenServiceGrpc.getDeleteNotificationMethod) == null) {
      synchronized (TokenServiceGrpc.class) {
        if ((getDeleteNotificationMethod = TokenServiceGrpc.getDeleteNotificationMethod) == null) {
          TokenServiceGrpc.getDeleteNotificationMethod = getDeleteNotificationMethod = 
              io.grpc.MethodDescriptor.<project.node.Token.NodeDescription, project.node.Token.ACK>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "project.node.TokenService", "deleteNotification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.node.Token.NodeDescription.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.node.Token.ACK.getDefaultInstance()))
                  .setSchemaDescriptor(new TokenServiceMethodDescriptorSupplier("deleteNotification"))
                  .build();
          }
        }
     }
     return getDeleteNotificationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TokenServiceStub newStub(io.grpc.Channel channel) {
    return new TokenServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TokenServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TokenServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TokenServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TokenServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TokenServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendToken(project.node.Token.TokenObject request,
        io.grpc.stub.StreamObserver<project.node.Token.ACK> responseObserver) {
      asyncUnimplementedUnaryCall(getSendTokenMethodHelper(), responseObserver);
    }

    /**
     */
    public void insertNotification(project.node.Token.NodeDescription request,
        io.grpc.stub.StreamObserver<project.node.Token.ACK> responseObserver) {
      asyncUnimplementedUnaryCall(getInsertNotificationMethodHelper(), responseObserver);
    }

    /**
     */
    public void deleteNotification(project.node.Token.NodeDescription request,
        io.grpc.stub.StreamObserver<project.node.Token.ACK> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteNotificationMethodHelper(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendTokenMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                project.node.Token.TokenObject,
                project.node.Token.ACK>(
                  this, METHODID_SEND_TOKEN)))
          .addMethod(
            getInsertNotificationMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                project.node.Token.NodeDescription,
                project.node.Token.ACK>(
                  this, METHODID_INSERT_NOTIFICATION)))
          .addMethod(
            getDeleteNotificationMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                project.node.Token.NodeDescription,
                project.node.Token.ACK>(
                  this, METHODID_DELETE_NOTIFICATION)))
          .build();
    }
  }

  /**
   */
  public static final class TokenServiceStub extends io.grpc.stub.AbstractStub<TokenServiceStub> {
    private TokenServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TokenServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TokenServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TokenServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendToken(project.node.Token.TokenObject request,
        io.grpc.stub.StreamObserver<project.node.Token.ACK> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendTokenMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void insertNotification(project.node.Token.NodeDescription request,
        io.grpc.stub.StreamObserver<project.node.Token.ACK> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInsertNotificationMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteNotification(project.node.Token.NodeDescription request,
        io.grpc.stub.StreamObserver<project.node.Token.ACK> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteNotificationMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TokenServiceBlockingStub extends io.grpc.stub.AbstractStub<TokenServiceBlockingStub> {
    private TokenServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TokenServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TokenServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TokenServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public project.node.Token.ACK sendToken(project.node.Token.TokenObject request) {
      return blockingUnaryCall(
          getChannel(), getSendTokenMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public project.node.Token.ACK insertNotification(project.node.Token.NodeDescription request) {
      return blockingUnaryCall(
          getChannel(), getInsertNotificationMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public project.node.Token.ACK deleteNotification(project.node.Token.NodeDescription request) {
      return blockingUnaryCall(
          getChannel(), getDeleteNotificationMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TokenServiceFutureStub extends io.grpc.stub.AbstractStub<TokenServiceFutureStub> {
    private TokenServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TokenServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TokenServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TokenServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<project.node.Token.ACK> sendToken(
        project.node.Token.TokenObject request) {
      return futureUnaryCall(
          getChannel().newCall(getSendTokenMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<project.node.Token.ACK> insertNotification(
        project.node.Token.NodeDescription request) {
      return futureUnaryCall(
          getChannel().newCall(getInsertNotificationMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<project.node.Token.ACK> deleteNotification(
        project.node.Token.NodeDescription request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteNotificationMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_TOKEN = 0;
  private static final int METHODID_INSERT_NOTIFICATION = 1;
  private static final int METHODID_DELETE_NOTIFICATION = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TokenServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TokenServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_TOKEN:
          serviceImpl.sendToken((project.node.Token.TokenObject) request,
              (io.grpc.stub.StreamObserver<project.node.Token.ACK>) responseObserver);
          break;
        case METHODID_INSERT_NOTIFICATION:
          serviceImpl.insertNotification((project.node.Token.NodeDescription) request,
              (io.grpc.stub.StreamObserver<project.node.Token.ACK>) responseObserver);
          break;
        case METHODID_DELETE_NOTIFICATION:
          serviceImpl.deleteNotification((project.node.Token.NodeDescription) request,
              (io.grpc.stub.StreamObserver<project.node.Token.ACK>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class TokenServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TokenServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return project.node.Token.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TokenService");
    }
  }

  private static final class TokenServiceFileDescriptorSupplier
      extends TokenServiceBaseDescriptorSupplier {
    TokenServiceFileDescriptorSupplier() {}
  }

  private static final class TokenServiceMethodDescriptorSupplier
      extends TokenServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TokenServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TokenServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TokenServiceFileDescriptorSupplier())
              .addMethod(getSendTokenMethodHelper())
              .addMethod(getInsertNotificationMethodHelper())
              .addMethod(getDeleteNotificationMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
