package cn.go.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import cn.go.service.BaseService;
import cn.go.util.DbOptEnum;
import cn.go.util.Pager;
import cn.go.util.SqlAndArgs;
import cn.go.util.SqlTool;

import com.mysql.jdbc.Statement;

/**
 * 抽象服务层基础实现（添加，更新，删除，单属性获取，批量操作，统计，查询）
 * 
 * @author qingtian
 * 
 *         2013-2-27 上午10:25:18
 */
@SuppressWarnings("unchecked")
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private static final Log log = LogFactory.getLog(BaseServiceImpl.class);

	static {
		ConvertUtils.register(new SqlTimestampConverter(null),
				java.sql.Timestamp.class);
	}

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private Class<T> entityClass;

	{
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public String getEntityName() {
		return entityClass.getName();
	}

	public String getEntitySimpleName() {
		return entityClass.getSimpleName();
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	@Override
	public int add(final T t) {
		final SqlAndArgs saa = SqlTool.generateInsertSQL(t);
		SqlTool.showSqlAndArgs(getEntitySimpleName() + " [add] ", saa);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						saa.getSql(), Statement.RETURN_GENERATED_KEYS);
				// 设置值
				Map<String, Object> bean = SqlTool.listFields(t, false);
				String[] fields = bean.keySet()
						.toArray(new String[bean.size()]);
				for (int i = 0, len = fields.length; i < len; i++) {
					ps.setObject(i + 1, bean.get(fields[i]));
				}
				return ps;
			}

		}, keyHolder);
		int generateKey = keyHolder.getKey().intValue();
		return generateKey;
	}

	@Override
	public int update(T t) {
		SqlAndArgs saa = SqlTool.generateUpdateSQL(t);
		SqlTool.showSqlAndArgs(getEntitySimpleName() + " [update] ", saa);
		return jdbcTemplate.update(saa.getSql(), saa.getArgs());
	}

	@Override
	public int delete(T t) {
		SqlAndArgs saa = SqlTool.generateDeleteSQL(t);
		SqlTool.showSqlAndArgs(getEntitySimpleName() + " [delete] ", saa);
		return jdbcTemplate.update(saa.getSql(), saa.getArgs());
	}

	@Override
	public T get(String property, Object value, Map<String, String> order) {
		SqlAndArgs saa = null;
		try {
			final T model = (T) Class.forName(getEntityName()).newInstance();
			saa = SqlTool.generateGetSQL(model, property, value, order);
			SqlTool.showSqlAndArgs(getEntitySimpleName() + " [get] ", saa);

			List<T> dataList = jdbcTemplate.query(saa.getSql(), saa.getArgs(),
					new RowMapper<T>() {
						@Override
						public T mapRow(ResultSet rs, int index)
								throws SQLException {
							Map<String, Object> fields = SqlTool.listFields(
									model, true);
							for (String key : fields.keySet()) {
								try {
									BeanUtils.setProperty(model, key,
											rs.getObject(key));
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									e.printStackTrace();
								}
							}
							return model;
						}

					});
			if (dataList.size() > 0)
				return dataList.get(0);
		} catch (InstantiationException e) {
			log.error(this, e);
		} catch (IllegalAccessException e) {
			log.error(this, e);
		} catch (ClassNotFoundException e) {
			log.error(this, e);
		}
		return null;
	}

	@Override
	public List<T> batchHandle(List<T> data, DbOptEnum doe) {
		List<T> effectObject = new ArrayList<T>();
		// 不同操作
		if (doe == DbOptEnum.INSERT) {
			for (T model : data) {
				if (add(model) > 0)
					effectObject.add(model);
			}
		} else if (doe == DbOptEnum.UPDATE) {
			for (T model : data) {
				if (update(model) > 0)
					effectObject.add(model);
			}
		} else if (doe == DbOptEnum.DELETE) {
			for (T model : data) {
				if (delete(model) > 0)
					effectObject.add(model);
			}
		}
		return effectObject;
	}

	@Override
	public int totalCount(String sql, Object... args) {
		SqlAndArgs saa = new SqlAndArgs(sql, args);
		SqlTool.showSqlAndArgs(getEntitySimpleName() + " [totalCount] ",
				saa.getSql(), saa.getArgs());
		return jdbcTemplate.queryForInt(sql, args);
	}

	@Override
	public List<T> list(String sql, Pager pager, Object... args) {
		SqlAndArgs saa = SqlTool.generateListSQL(sql, pager, args);
		SqlTool.showSqlAndArgs(getEntitySimpleName() + " [list] ",
				saa.getSql(), saa.getArgs());

		RowMapper<T> rowmapper = new RowMapper<T>() {
			@Override
			public T mapRow(ResultSet rs, int index) throws SQLException {
				T model = null;
				try {
					model = (T) Class.forName(getEntityName()).newInstance();
					Map<String, Object> fields = SqlTool
							.listFields(model, true);
					for (String key : fields.keySet()) {
						try {
							BeanUtils
									.setProperty(model, key, rs.getObject(key));
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				} catch (InstantiationException e) {
					log.error(this, e);
				} catch (IllegalAccessException e) {
					log.error(this, e);
				} catch (ClassNotFoundException e) {
					log.error(this, e);
				}
				return model;
			}

		};

		if (saa.getArgs() == null) {
			return jdbcTemplate.query(saa.getSql(), rowmapper);
		}
		return jdbcTemplate.query(saa.getSql(), saa.getArgs(), rowmapper);
	}

	@Override
	public List<Map<String, Object>> listForMap(String sql, Pager pager,
			Object... args) {
		SqlAndArgs saa = SqlTool.generateListSQL(sql, null, args);
		SqlTool.showSqlAndArgs(getEntitySimpleName() + " [listForMap] ",
				saa.getSql(), saa.getArgs());
		if (saa.getArgs() == null) {
			return jdbcTemplate.queryForList(saa.getSql());
		}
		return jdbcTemplate.queryForList(saa.getSql(), saa.getArgs());
	}

}
