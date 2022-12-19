package utils.result;

import java.util.function.Supplier;
import lombok.Data;

@Data
public class Result<T> {
	private final int error;
	private final String message;
	private final T content;
	private final String fullMessage;

	public Result(T content) {
		this(0,  "OK", content);
	}

	public Result(int err, String msg) {
		this(err, msg, null);
	}

	public Result(int err, String msg, T content) {
		this.error = err;
		this.message = msg;
		this.content = content;
		String contentClass = content == null ? "NULL" : content.getClass().toString();
		this.fullMessage = String.format("%s [%d] %s", contentClass, error, message);
	}

	public T getContent() {
		if (content == null) {
			panic();
		}

		return content;
	}

	public T getContentUnchecked() {
		return content;
	}

	public T getContentOr(T other) {
		if (content == null) {
			return other;
		}

		return content;
	}

	public T getContentOrElse(Supplier<T> fn) {
		if (content == null) {
			return fn.get();
		}

		return content;
	}

	public void throwLoud() throws LoudResultException {
		throw new LoudResultException(fullMessage);
	}

	public void throwSilent() throws SilentResultException {
		throw new SilentResultException(fullMessage);
	}

	public void panic() {
		throw new Panic(fullMessage);
	}
}
