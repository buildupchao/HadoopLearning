package mapreduce.topk2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class Person implements WritableComparable<Person> {

	private String name;

	private Integer age;

	private String gender;

	private Integer score;

	public Person() {
	}

	public Person(String name, Integer age, String gender, Integer score) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.score = score;
	}

	public void set(String name, Integer age, String gender, Integer score) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.score = score;
	}

	@Override
	public String toString() {
		return "\t" + age + "\t" + gender + "\t" + score;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(name);
		out.writeInt(age);
		out.writeUTF(gender);
		out.writeInt(score);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.name = in.readUTF();
		this.age = in.readInt();
		this.gender = in.readUTF();
		this.score = in.readInt();
	}

	/**
	 * First, if gender does not equal then sort by gender asc.
	 * Second, if score equals then sort by name asc, otherwise sort by score desc.
	 */
	@Override
	public int compareTo(Person o) {
		if (!this.gender.equals(o.gender)) {
			return this.gender.compareTo(o.gender);
		} else if (this.score != o.score) {
			return -this.score.compareTo(o.score);
		} else {
			return this.name.compareTo(o.name);
		}
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public Integer getScore() {
		return score;
	}
}
