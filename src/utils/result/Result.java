package utils.result;

import java.util.function.Supplier;

import lombok.Data;

@Data
public class Result<T> {
	private final int error;
	private final String message;
	private final T content;
	private final String fullMessage;

	public Result(T cont) {
		this.error = 0;
		this.message = "OK";
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
		try {
			throw new Panic(fullMessage);
		} catch (Panic p) {
			p.printStackTrace();
			System.exit(error);
		}
	}
}
