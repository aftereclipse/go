package cn.go.util;

import java.util.Arrays;

public final class SqlAndArgs {

	private String sql;
	private Object[] args;

	public SqlAndArgs(String sql, Object[] args) {
		this.sql = sql;
		if (args != null) {
			int len = args.length;
			this.args = new Object[len];
			System.arraycopy(args, 0, this.args, 0, args.length);
		} else {
			this.args = new Object[] {};
		}
	}

	public String getSql() {
		return sql;
	}

	public Object[] getArgs() {
		return args;
	}

	@Override
	public String toString() {
		return "SqlAndArgs [sql=" + sql + ", args=" + Arrays.toString(args)
				+ "]";
	}

}
