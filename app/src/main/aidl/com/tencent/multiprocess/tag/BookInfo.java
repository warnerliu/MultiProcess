package com.tencent.multiprocess.tag;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by warner on 07/02/2017.
 */

public class BookInfo implements Parcelable {
	private String name;
	private int price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "name === " + name + " ___ price === " + price;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeInt(this.price);
	}

	public BookInfo() {
	}

	protected BookInfo(Parcel in) {
		this.name = in.readString();
		this.price = in.readInt();
	}

	/**
	 * 如果要使用定向tag中的out，必须要有readFromParcel方法
	 */
	public void readFromParcel(Parcel in) {
		this.name = in.readString();
		this.price = in.readInt();
	}

	public static final Parcelable.Creator<BookInfo> CREATOR = new Parcelable.Creator<BookInfo>() {
		@Override
		public BookInfo createFromParcel(Parcel source) {
			return new BookInfo(source);
		}

		@Override
		public BookInfo[] newArray(int size) {
			return new BookInfo[size];
		}
	};
}
