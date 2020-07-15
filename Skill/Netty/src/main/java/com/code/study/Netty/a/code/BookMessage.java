package com.code.study.Netty.a.code;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Book.proto

public final class BookMessage {
	private BookMessage() {
	}

	public static void registerAllExtensions(
			com.google.protobuf.ExtensionRegistryLite registry) {
	}

	public static void registerAllExtensions(
			com.google.protobuf.ExtensionRegistry registry) {
		registerAllExtensions(
				(com.google.protobuf.ExtensionRegistryLite) registry);
	}

	public interface BookOrBuilder extends
			// @@protoc_insertion_point(interface_extends:Book)
			com.google.protobuf.MessageOrBuilder {

		/**
		 * <code>optional int32 id = 1;</code>
		 */
		int getId();

		/**
		 * <code>optional string name = 2;</code>
		 */
		String getName();

		/**
		 * <code>optional string name = 2;</code>
		 */
		com.google.protobuf.ByteString
		getNameBytes();
	}

	/**
	 * Protobuf type {@code Book}
	 */
	public static final class Book extends
			com.google.protobuf.GeneratedMessageV3 implements
			// @@protoc_insertion_point(message_implements:Book)
			BookOrBuilder {
		// Use Book.newBuilder() to construct.
		private Book(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
			super(builder);
		}

		private Book() {
			id_ = 0;
			name_ = "";
		}

		@Override
		public final com.google.protobuf.UnknownFieldSet
		getUnknownFields() {
			return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
		}

		private Book(
				com.google.protobuf.CodedInputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws com.google.protobuf.InvalidProtocolBufferException {
			this();
			int mutable_bitField0_ = 0;
			try {
				boolean done = false;
				while (!done) {
					int tag = input.readTag();
					switch (tag) {
						case 0:
							done = true;
							break;
						default: {
							if (!input.skipField(tag)) {
								done = true;
							}
							break;
						}
						case 8: {

							id_ = input.readInt32();
							break;
						}
						case 18: {
							String s = input.readStringRequireUtf8();

							name_ = s;
							break;
						}
					}
				}
			} catch (com.google.protobuf.InvalidProtocolBufferException e) {
				throw e.setUnfinishedMessage(this);
			} catch (java.io.IOException e) {
				throw new com.google.protobuf.InvalidProtocolBufferException(
						e).setUnfinishedMessage(this);
			} finally {
				makeExtensionsImmutable();
			}
		}

		public static final com.google.protobuf.Descriptors.Descriptor
		getDescriptor() {
			return BookMessage.internal_static_Book_descriptor;
		}

		protected FieldAccessorTable
		internalGetFieldAccessorTable() {
			return BookMessage.internal_static_Book_fieldAccessorTable
					.ensureFieldAccessorsInitialized(
							BookMessage.Book.class, BookMessage.Book.Builder.class);
		}

		public static final int ID_FIELD_NUMBER = 1;
		private int id_;

		/**
		 * <code>optional int32 id = 1;</code>
		 */
		public int getId() {
			return id_;
		}

		public static final int NAME_FIELD_NUMBER = 2;
		private volatile Object name_;

		/**
		 * <code>optional string name = 2;</code>
		 */
		public String getName() {
			Object ref = name_;
			if (ref instanceof String) {
				return (String) ref;
			} else {
				com.google.protobuf.ByteString bs =
						(com.google.protobuf.ByteString) ref;
				String s = bs.toStringUtf8();
				name_ = s;
				return s;
			}
		}

		/**
		 * <code>optional string name = 2;</code>
		 */
		public com.google.protobuf.ByteString
		getNameBytes() {
			Object ref = name_;
			if (ref instanceof String) {
				com.google.protobuf.ByteString b =
						com.google.protobuf.ByteString.copyFromUtf8(
								(String) ref);
				name_ = b;
				return b;
			} else {
				return (com.google.protobuf.ByteString) ref;
			}
		}

		private byte memoizedIsInitialized = -1;

		public final boolean isInitialized() {
			byte isInitialized = memoizedIsInitialized;
			if (isInitialized == 1) return true;
			if (isInitialized == 0) return false;

			memoizedIsInitialized = 1;
			return true;
		}

		public void writeTo(com.google.protobuf.CodedOutputStream output)
				throws java.io.IOException {
			if (id_ != 0) {
				output.writeInt32(1, id_);
			}
			if (!getNameBytes().isEmpty()) {
				com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
			}
		}

		public int getSerializedSize() {
			int size = memoizedSize;
			if (size != -1) return size;

			size = 0;
			if (id_ != 0) {
				size += com.google.protobuf.CodedOutputStream
						.computeInt32Size(1, id_);
			}
			if (!getNameBytes().isEmpty()) {
				size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
			}
			memoizedSize = size;
			return size;
		}

		private static final long serialVersionUID = 0L;

		@Override
		public boolean equals(final Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof BookMessage.Book)) {
				return super.equals(obj);
			}
			BookMessage.Book other = (BookMessage.Book) obj;

			boolean result = true;
			result = result && (getId()
					== other.getId());
			result = result && getName()
					.equals(other.getName());
			return result;
		}

		@Override
		public int hashCode() {
			if (memoizedHashCode != 0) {
				return memoizedHashCode;
			}
			int hash = 41;
			hash = (19 * hash) + getDescriptorForType().hashCode();
			hash = (37 * hash) + ID_FIELD_NUMBER;
			hash = (53 * hash) + getId();
			hash = (37 * hash) + NAME_FIELD_NUMBER;
			hash = (53 * hash) + getName().hashCode();
			hash = (29 * hash) + unknownFields.hashCode();
			memoizedHashCode = hash;
			return hash;
		}

		public static BookMessage.Book parseFrom(
				com.google.protobuf.ByteString data)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return PARSER.parseFrom(data);
		}

		public static BookMessage.Book parseFrom(
				com.google.protobuf.ByteString data,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return PARSER.parseFrom(data, extensionRegistry);
		}

		public static BookMessage.Book parseFrom(byte[] data)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return PARSER.parseFrom(data);
		}

		public static BookMessage.Book parseFrom(
				byte[] data,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return PARSER.parseFrom(data, extensionRegistry);
		}

		public static BookMessage.Book parseFrom(java.io.InputStream input)
				throws java.io.IOException {
			return com.google.protobuf.GeneratedMessageV3
					.parseWithIOException(PARSER, input);
		}

		public static BookMessage.Book parseFrom(
				java.io.InputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws java.io.IOException {
			return com.google.protobuf.GeneratedMessageV3
					.parseWithIOException(PARSER, input, extensionRegistry);
		}

		public static BookMessage.Book parseDelimitedFrom(java.io.InputStream input)
				throws java.io.IOException {
			return com.google.protobuf.GeneratedMessageV3
					.parseDelimitedWithIOException(PARSER, input);
		}

		public static BookMessage.Book parseDelimitedFrom(
				java.io.InputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws java.io.IOException {
			return com.google.protobuf.GeneratedMessageV3
					.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
		}

		public static BookMessage.Book parseFrom(
				com.google.protobuf.CodedInputStream input)
				throws java.io.IOException {
			return com.google.protobuf.GeneratedMessageV3
					.parseWithIOException(PARSER, input);
		}

		public static BookMessage.Book parseFrom(
				com.google.protobuf.CodedInputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws java.io.IOException {
			return com.google.protobuf.GeneratedMessageV3
					.parseWithIOException(PARSER, input, extensionRegistry);
		}

		public Builder newBuilderForType() {
			return newBuilder();
		}

		public static Builder newBuilder() {
			return DEFAULT_INSTANCE.toBuilder();
		}

		public static Builder newBuilder(BookMessage.Book prototype) {
			return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
		}

		public Builder toBuilder() {
			return this == DEFAULT_INSTANCE
					? new Builder() : new Builder().mergeFrom(this);
		}

		@Override
		protected Builder newBuilderForType(
				BuilderParent parent) {
			Builder builder = new Builder(parent);
			return builder;
		}

		/**
		 * Protobuf type {@code Book}
		 */
		public static final class Builder extends
				com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
				// @@protoc_insertion_point(builder_implements:Book)
				BookMessage.BookOrBuilder {
			public static final com.google.protobuf.Descriptors.Descriptor
			getDescriptor() {
				return BookMessage.internal_static_Book_descriptor;
			}

			protected FieldAccessorTable
			internalGetFieldAccessorTable() {
				return BookMessage.internal_static_Book_fieldAccessorTable
						.ensureFieldAccessorsInitialized(
								BookMessage.Book.class, BookMessage.Book.Builder.class);
			}

			// Construct using BookMessage.Book.newBuilder()
			private Builder() {
				maybeForceBuilderInitialization();
			}

			private Builder(
					BuilderParent parent) {
				super(parent);
				maybeForceBuilderInitialization();
			}

			private void maybeForceBuilderInitialization() {
				if (com.google.protobuf.GeneratedMessageV3
						.alwaysUseFieldBuilders) {
				}
			}

			public Builder clear() {
				super.clear();
				id_ = 0;

				name_ = "";

				return this;
			}

			public com.google.protobuf.Descriptors.Descriptor
			getDescriptorForType() {
				return BookMessage.internal_static_Book_descriptor;
			}

			public BookMessage.Book getDefaultInstanceForType() {
				return BookMessage.Book.getDefaultInstance();
			}

			public BookMessage.Book build() {
				BookMessage.Book result = buildPartial();
				if (!result.isInitialized()) {
					throw newUninitializedMessageException(result);
				}
				return result;
			}

			public BookMessage.Book buildPartial() {
				BookMessage.Book result = new BookMessage.Book(this);
				result.id_ = id_;
				result.name_ = name_;
				onBuilt();
				return result;
			}

			public Builder clone() {
				return (Builder) super.clone();
			}

			public Builder setField(
					com.google.protobuf.Descriptors.FieldDescriptor field,
					Object value) {
				return (Builder) super.setField(field, value);
			}

			public Builder clearField(
					com.google.protobuf.Descriptors.FieldDescriptor field) {
				return (Builder) super.clearField(field);
			}

			public Builder clearOneof(
					com.google.protobuf.Descriptors.OneofDescriptor oneof) {
				return (Builder) super.clearOneof(oneof);
			}

			public Builder setRepeatedField(
					com.google.protobuf.Descriptors.FieldDescriptor field,
					int index, Object value) {
				return (Builder) super.setRepeatedField(field, index, value);
			}

			public Builder addRepeatedField(
					com.google.protobuf.Descriptors.FieldDescriptor field,
					Object value) {
				return (Builder) super.addRepeatedField(field, value);
			}

			public Builder mergeFrom(com.google.protobuf.Message other) {
				if (other instanceof BookMessage.Book) {
					return mergeFrom((BookMessage.Book) other);
				} else {
					super.mergeFrom(other);
					return this;
				}
			}

			public Builder mergeFrom(BookMessage.Book other) {
				if (other == BookMessage.Book.getDefaultInstance()) return this;
				if (other.getId() != 0) {
					setId(other.getId());
				}
				if (!other.getName().isEmpty()) {
					name_ = other.name_;
					onChanged();
				}
				onChanged();
				return this;
			}

			public final boolean isInitialized() {
				return true;
			}

			public Builder mergeFrom(
					com.google.protobuf.CodedInputStream input,
					com.google.protobuf.ExtensionRegistryLite extensionRegistry)
					throws java.io.IOException {
				BookMessage.Book parsedMessage = null;
				try {
					parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
				} catch (com.google.protobuf.InvalidProtocolBufferException e) {
					parsedMessage = (BookMessage.Book) e.getUnfinishedMessage();
					throw e.unwrapIOException();
				} finally {
					if (parsedMessage != null) {
						mergeFrom(parsedMessage);
					}
				}
				return this;
			}

			private int id_;

			/**
			 * <code>optional int32 id = 1;</code>
			 */
			public int getId() {
				return id_;
			}

			/**
			 * <code>optional int32 id = 1;</code>
			 */
			public Builder setId(int value) {

				id_ = value;
				onChanged();
				return this;
			}

			/**
			 * <code>optional int32 id = 1;</code>
			 */
			public Builder clearId() {

				id_ = 0;
				onChanged();
				return this;
			}

			private Object name_ = "";

			/**
			 * <code>optional string name = 2;</code>
			 */
			public String getName() {
				Object ref = name_;
				if (!(ref instanceof String)) {
					com.google.protobuf.ByteString bs =
							(com.google.protobuf.ByteString) ref;
					String s = bs.toStringUtf8();
					name_ = s;
					return s;
				} else {
					return (String) ref;
				}
			}

			/**
			 * <code>optional string name = 2;</code>
			 */
			public com.google.protobuf.ByteString
			getNameBytes() {
				Object ref = name_;
				if (ref instanceof String) {
					com.google.protobuf.ByteString b =
							com.google.protobuf.ByteString.copyFromUtf8(
									(String) ref);
					name_ = b;
					return b;
				} else {
					return (com.google.protobuf.ByteString) ref;
				}
			}

			/**
			 * <code>optional string name = 2;</code>
			 */
			public Builder setName(
					String value) {
				if (value == null) {
					throw new NullPointerException();
				}

				name_ = value;
				onChanged();
				return this;
			}

			/**
			 * <code>optional string name = 2;</code>
			 */
			public Builder clearName() {

				name_ = getDefaultInstance().getName();
				onChanged();
				return this;
			}

			/**
			 * <code>optional string name = 2;</code>
			 */
			public Builder setNameBytes(
					com.google.protobuf.ByteString value) {
				if (value == null) {
					throw new NullPointerException();
				}
				checkByteStringIsUtf8(value);

				name_ = value;
				onChanged();
				return this;
			}

			public final Builder setUnknownFields(
					final com.google.protobuf.UnknownFieldSet unknownFields) {
				return this;
			}

			public final Builder mergeUnknownFields(
					final com.google.protobuf.UnknownFieldSet unknownFields) {
				return this;
			}


			// @@protoc_insertion_point(builder_scope:Book)
		}

		// @@protoc_insertion_point(class_scope:Book)
		private static final BookMessage.Book DEFAULT_INSTANCE;

		static {
			DEFAULT_INSTANCE = new BookMessage.Book();
		}

		public static BookMessage.Book getDefaultInstance() {
			return DEFAULT_INSTANCE;
		}

		private static final com.google.protobuf.Parser<Book>
				PARSER = new com.google.protobuf.AbstractParser<Book>() {
			public Book parsePartialFrom(
					com.google.protobuf.CodedInputStream input,
					com.google.protobuf.ExtensionRegistryLite extensionRegistry)
					throws com.google.protobuf.InvalidProtocolBufferException {
				return new Book(input, extensionRegistry);
			}
		};

		public static com.google.protobuf.Parser<Book> parser() {
			return PARSER;
		}

		@Override
		public com.google.protobuf.Parser<Book> getParserForType() {
			return PARSER;
		}

		public BookMessage.Book getDefaultInstanceForType() {
			return DEFAULT_INSTANCE;
		}

	}

	private static final com.google.protobuf.Descriptors.Descriptor
			internal_static_Book_descriptor;
	private static final
	com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
			internal_static_Book_fieldAccessorTable;

	public static com.google.protobuf.Descriptors.FileDescriptor
	getDescriptor() {
		return descriptor;
	}

	private static com.google.protobuf.Descriptors.FileDescriptor
			descriptor;

	static {
		String[] descriptorData = {
				"\n\nBook.proto\" \n\004Book\022\n\n\002id\030\001 \001(\005\022\014\n\004name" +
						"\030\002 \001(\tB\rB\013BookMessageb\006proto3"
		};
		com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
				new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
					public com.google.protobuf.ExtensionRegistry assignDescriptors(
							com.google.protobuf.Descriptors.FileDescriptor root) {
						descriptor = root;
						return null;
					}
				};
		com.google.protobuf.Descriptors.FileDescriptor
				.internalBuildGeneratedFileFrom(descriptorData,
						new com.google.protobuf.Descriptors.FileDescriptor[]{
						}, assigner);
		internal_static_Book_descriptor =
				getDescriptor().getMessageTypes().get(0);
		internal_static_Book_fieldAccessorTable = new
				com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_Book_descriptor,
				new String[]{"Id", "Name",});
	}

	// @@protoc_insertion_point(outer_class_scope)
}
