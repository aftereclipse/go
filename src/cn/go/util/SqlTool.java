package cn.go.util;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SqlTool {

	private static final Log log = LogFactory.getLog("sql");

	public static void showSqlAndArgs(String prefix, SqlAndArgs saa) {
		showSqlAndArgs(prefix, saa.getSql(), saa.getArgs());
	}

	public static void showSqlAndArgs(String prefix, String sql, Object[] args) {
		log.info(prefix + "'s sql: " + sql);
		log.info(prefix + "'s args: " + ArrayUtils.toString(args) + "");
	}

	public static String tableName(Class<? extends Object> clz) {
		String fullName = clz.getName();
		int startDocPos = fullName.lastIndexOf('.');
		String className = fullName.substring(startDocPos + 1);
		className = className.toLowerCase();
		return className;
	}

	public static Map<String, Object> listFields(Object model, boolean getAll) {
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> props = BeanUtils.describe(model);
			if (!getAll) {
				// id <=0 表示是插入
				if (Integer.parseInt(BeanUtils.getProperty(model, "id")) <= 0)
					props.remove("id");
			}
			props.remove("class");
			return props;
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	public static String selectedFiled(Object model) {
		StringBuilder sb = new StringBuilder();
		Map<String, Object> fileds = listFields(model, true);
		boolean first = true;
		for (String key : fileds.keySet()) {
			if (first) {
				sb.append(key);
				first = false;
			} else {
				sb.append("," + key);
			}
		}
		return sb.toString();
	}

	public static SqlAndArgs generateInsertSQL(Object model) {
		Map<String, Object> bean = listFields(model, false);
		String[] fields = bean.keySet().toArray(new String[bean.size()]);
		// sql
		StringBuilder sql = new StringBuilder("insert into ");
		sql.append(tableName(model.getClass()));
		sql.append('(');
		int fieldsLength = fields.length;
		for (int i = 0; i < fieldsLength; i++) {
			if (i > 0)
				sql.append(',');
			sql.append(fields[i]);
		}
		sql.append(") values(");
		for (int i = 0; i < fieldsLength; i++) {
			if (i > 0)
				sql.append(',');
			sql.append('?');
		}
		sql.append(')');
		// args
		Object[] args = new Object[fields.length];
		for (int i = 0; i < fieldsLength; i++) {
			args[i] = bean.get(fields[i]);
		}
		return new SqlAndArgs(sql.toString(), args);
	}

	public static SqlAndArgs generateUpdateSQL(Object model) {
		Map<String, Object> bean = listFields(model, false);
		// copy fields without property 'id'
		Map<String, Object> cp_bean = new HashMap<String, Object>();
		for (String key : bean.keySet()) {
			if (!key.equals("id")) {
				Object value = bean.get(key);
				cp_bean.put(key, value);
			}
		}
		String[] fields = cp_bean.keySet().toArray(new String[cp_bean.size()]);
		// sql
		StringBuilder sql = new StringBuilder("update ");
		sql.append(tableName(model.getClass()));
		sql.append(" set ");
		int fieldsLength = fields.length;
		for (int i = 0; i < fieldsLength; i++) {
			if (i > 0)
				sql.append(',');
			sql.append(fields[i] + "=? ");
		}
		sql.append(" where id = ? ");
		// args
		Object[] args = new Object[fields.length + 1];
		for (int i = 0; i < fieldsLength; i++) {
			args[i] = cp_bean.get(fields[i]);
		}
		// args: id
		try {
			args[args.length - 1] = BeanUtils.getProperty(model, "id");
		} catch (IllegalAccessException e) {
			log.error(e, e.getCause());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			log.error(e, e.getCause());
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			log.error(e, e.getCause());
			e.printStackTrace();
		}
		return new SqlAndArgs(sql.toString(), args);
	}

	public static SqlAndArgs generateDeleteSQL(Object model) {
		StringBuilder sql = new StringBuilder("delete from  ");
		sql.append(tableName(model.getClass()));
		sql.append(" where id = ? ");
		SqlAndArgs saa = null;
		try {
			saa = new SqlAndArgs(sql.toString(),
					new Object[] { BeanUtils.getProperty(model, "id") });
		} catch (IllegalAccessException e) {
			log.error(e, e.getCause());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			log.error(e, e.getCause());
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			log.error(e, e.getCause());
			e.printStackTrace();
		}
		return saa;
	}

	public static SqlAndArgs generateGetSQL(Object model, String property,
			Object value, Map<String, String> order) {
		// sql
		StringBuilder sql = new StringBuilder("select ");
		String tname = tableName(model.getClass());
		sql.append(selectedFiled(model));
		sql.append("  from   ");
		sql.append(tname);
		try {
			// where
			Class<? extends Object> clazz = (Class<? extends Object>) model
					.getClass();
			String typeName = clazz.getDeclaredField(property).getType()
					.getName();
			if (StringUtils.equals(typeName, "java.lang.String"))
				sql.append(" where " + property + " like ? ");
			else
				sql.append(" where " + property + " = ? ");
			// order
			if (order != null && order.size() > 0) {
				sql.append(" order by ");
				boolean first = true;
				for (Object key : order.keySet()) {
					String v = order.get(key);
					if (first) {
						sql.append(key + " " + v);
						first = false;
					} else
						sql.append(" , " + key + " " + v);
				}
			}
			// 只根据排序结果获取第一条
			sql.append(" limit 0,1 ");

		} catch (SecurityException e) {
			log.error(e, e.getCause());
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			log.error(e, e.getCause());
			e.printStackTrace();
		}
		return new SqlAndArgs(sql.toString(), new Object[] { value });
	}

	public static SqlAndArgs generateListSQL(String sql, Pager pager,
			Object... args) {
		if (pager == null) {
			return new SqlAndArgs(sql.toString(), args);
		} else {
			if (args == null)
				args = new Object[] {};
			Object[] exeArgs = new Object[args.length + 2];
			System.arraycopy(args, 0, exeArgs, 0, args.length);
			exeArgs[args.length] = (pager.getCurrentPage() - 1)
					* pager.getCountPerPage();
			exeArgs[args.length + 1] = pager.getCountPerPage();
			return new SqlAndArgs(sql.toString(), exeArgs);
		}
	}
}
