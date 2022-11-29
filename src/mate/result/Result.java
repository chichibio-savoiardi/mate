package mate.result;

import lombok.Data;

@Data
public class Result<T> {
	private final int error;
	private final String message;
	private final T content;
	private final String fullMessage;

	public Result(T cont) {
		this.error = 0;
		this.message = "";
		this.content = cont;
		this.fullMessage = String.format("%s [%d] %s", content.getClass(), error, message);
	}

	public Result(int err, String msg) {
		this.error = err;
		this.message = msg;
		this.content = null;
		this.fullMessage = String.format("%s [%d] %s", content.getClass(), error, message);
	}

	public Result(int err, String msg, T content) {
		this.error = err;
		this.message = msg;
		this.content = content;
		this.fullMessage = String.format("%s [%d] %s", content.getClass(), error, message);
	}

	public void throwLoud() throws Exception {
		throw new Exception(fullMessage);
	}

	public void throwSilent() throws RuntimeException {
		throw new RuntimeException(fullMessage);
	}
}
