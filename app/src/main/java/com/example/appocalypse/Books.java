package com.example.appocalypse;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name = "GoodreadsResponse", strict = false)
class apiResponse {
    @Element(name = "results")
    @Path("search")
    private result result;

    public result getResult() {
        return result;
    }

    public void setResult(com.example.appocalypse.result result) {
        this.result = result;
    }


    public String Author(int position) {
        return result.getWork().get(position).getBest_book().getAuthor()
                .getName();
    }

    public String Title(int position) {
        return result.getWork().get(position).getBest_book().getTitle();
    }

    public String ImageUrl(int position) {
        return result.getWork().get(position).getBest_book().getImage_url();
    }

}

@Root(name = "results", strict = false)
class result {
    @ElementList(inline = true, required = false)
    private List<Work> Work;

    public List<Work> getWork() {
        return Work;
    }

    public void setWork(List<Work> workObject) {
        this.Work = workObject;
    }
}



@Entity
@Root(name = "best_book", strict = false)
public class Books implements Parcelable {
    @ColumnInfo(name = "id")
    @PrimaryKey
    @Element(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    @Element(name = "title")
    private String title;

    @ColumnInfo(name = "author")
    @Element(name = "author")
    @TypeConverters({authorConverter.class})
    private Author Author;

    @ColumnInfo(name = "image_url")
    @Element(name = "image_url")
    private String image_url;

//    @TypeConverters({statusConverter.class})
    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "started")
    private String startDate;

    @ColumnInfo(name = "ended")
    private String endDate;

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return Author;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author authorObject) {
        this.Author = authorObject;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringArray(new String[]{title, Author.getName(), image_url, status,
                startDate, endDate});
    }

    public Books(Parcel source) {
        String[] args = new String[6];
        source.readStringArray(args);
        title = args[0];
        Author author = new Author();
        author.setName(args[1]);
        Author = author;
        image_url = args[2];
        status = args[3];
        startDate = args[4];
        startDate = args[5];
        id = source.readInt();
    }

    public static final Parcelable.Creator<Books> CREATOR = new Parcelable.Creator<Books>() {

        @Override
        public Books createFromParcel(Parcel source) {
            return new Books(source);
        }

        @Override
        public Books[] newArray(int size) {
            return new Books[size];
        }

    };

    Books() {
    }
}

class authorConverter {
    @TypeConverter
    public String authorToString(Author author) {
        return author.getName();
    }

    @TypeConverter
    public Author stringToAuthor(String name) {
        Author author = new Author();
        author.setName(name);
        return author;
    }
}
