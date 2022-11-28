package mate.result;

import lombok.Data;

@Data
public class Result<T> {
	private final int error;
	private final String message;
	private final T content;
	
	public Result(T cont) {
		this.content = cont;
		this.message = "";
		this.error = 0;
	}

	public Result(int err, String msg) {
		this.error = err;
		this.message = msg;
		this.content = null;
	}
	
	public Result(int err, String msg, T content) {
		this.error = err;
		this.message = msg;
		this.content = content;
	}
}
