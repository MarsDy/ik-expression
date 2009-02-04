/**
 * 
 */
package org.wltea.expression.op.define;

import org.wltea.expression.IllegalExpressionException;
import org.wltea.expression.datameta.BaseDataMeta;
import org.wltea.expression.datameta.Constant;
import org.wltea.expression.op.IOperatorExecution;
import org.wltea.expression.op.Operator;


/**
 * 加号操作实现
 * 加号操作包括了数学加法和字符窜连接
 * @author 林良益
 * @version 2.0
 */
public class Op_PLUS implements IOperatorExecution {

	public static final Operator THIS_OPERATOR = Operator.PLUS;
	
	public Constant execute(Constant[] args){
		
		if(args == null || args.length != 2){
			throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "参数个数不匹配");
		}

		Constant first = args[1];
		Constant second = args[0];
		if(first == null || second == null){
			throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
		}
		//集合类型EQ运算单独处理
		if(BaseDataMeta.DataType.DATATYPE_COLLECTION ==  first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_COLLECTION ==  second.getDataType()){
			//目前不支持集合EQ比较，（太麻烦鸟）.考虑使用后期使用函数实现
			throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数类型错误");

		}else if(BaseDataMeta.DataType.DATATYPE_STRING ==  first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_STRING == second.getDataType()){
			String firstString = "";
			String secondString = "";
			//字符窜连接运算,如果参数是null，则当作空字符窜处理
			if(null != first.getStringValue()){
				firstString = first.getStringValue();
			}
			if(null != second.getStringValue()){
				secondString = second.getStringValue();
			}
			String result = firstString + secondString;
			return new Constant(BaseDataMeta.DataType.DATATYPE_STRING , result);
			
		}else if(null == first.getDataValue() || null == second.getDataValue()){
			//抛NULL异常
			throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
		
		}else if( BaseDataMeta.DataType.DATATYPE_NULL ==  first.getDataType()
					|| BaseDataMeta.DataType.DATATYPE_NULL ==  second.getDataType()
					|| BaseDataMeta.DataType.DATATYPE_BOOLEAN ==  first.getDataType()
					|| BaseDataMeta.DataType.DATATYPE_BOOLEAN ==  second.getDataType()
					|| BaseDataMeta.DataType.DATATYPE_DATE ==  first.getDataType()
					|| BaseDataMeta.DataType.DATATYPE_DATE ==  second.getDataType()
					){
			//抛异常
			throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数类型错误"	);

		}else if(BaseDataMeta.DataType.DATATYPE_DOUBLE == first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_DOUBLE == second.getDataType()){
			
			Double result = first.getDoubleValue() + second.getDoubleValue();
			return new Constant(BaseDataMeta.DataType.DATATYPE_DOUBLE , result);
			
		}else if(BaseDataMeta.DataType.DATATYPE_FLOAT == first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_FLOAT == second.getDataType()){
			
			Float result = first.getFloatValue() + second.getFloatValue();
			return new Constant(BaseDataMeta.DataType.DATATYPE_FLOAT , result);
			
		}else if(BaseDataMeta.DataType.DATATYPE_LONG == first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_LONG == second.getDataType()){

			Long result = first.getLongValue() + second.getLongValue();
			return new Constant(BaseDataMeta.DataType.DATATYPE_LONG , result);
			
		}else {
	
			Integer result = first.getIntegerValue() + second.getIntegerValue();
			return new Constant(BaseDataMeta.DataType.DATATYPE_INT , result);
		}

	}

	public Constant verify(int opPositin, BaseDataMeta[] args)
			throws IllegalExpressionException {
		
		if(args == null){
			throw new IllegalArgumentException("运算操作符参数为空");
		}
		if(args.length != 2){
			//抛异常
			throw new IllegalExpressionException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数个数不匹配"
						, THIS_OPERATOR.getToken()
						, opPositin
					);
		}
		
		BaseDataMeta first = args[1];
		BaseDataMeta second = args[0];
		if(first == null || second == null){
			throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
		}
		
		//集合类型EQ运算单独处理
		if(BaseDataMeta.DataType.DATATYPE_COLLECTION ==  first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_COLLECTION ==  second.getDataType()){
			//目前不支持集合EQ比较，（太麻烦鸟）.考虑使用后期使用函数实现
			throw new IllegalExpressionException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数类型错误"
					, THIS_OPERATOR.getToken()
					, opPositin);

		}		

		if(BaseDataMeta.DataType.DATATYPE_STRING ==  first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_STRING == second.getDataType()){
			
			return new Constant(BaseDataMeta.DataType.DATATYPE_STRING , null);
			
		}else if( BaseDataMeta.DataType.DATATYPE_NULL ==  first.getDataType()
					|| BaseDataMeta.DataType.DATATYPE_NULL ==  second.getDataType()
					|| BaseDataMeta.DataType.DATATYPE_BOOLEAN ==  first.getDataType()
					|| BaseDataMeta.DataType.DATATYPE_BOOLEAN ==  second.getDataType()
					|| BaseDataMeta.DataType.DATATYPE_DATE ==  first.getDataType()
					|| BaseDataMeta.DataType.DATATYPE_DATE ==  second.getDataType()
					){
			//抛异常
			throw new IllegalExpressionException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数类型错误"
					, THIS_OPERATOR.getToken()
					, opPositin
					);

		}else if(BaseDataMeta.DataType.DATATYPE_DOUBLE == first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_DOUBLE == second.getDataType()){
			return new Constant(BaseDataMeta.DataType.DATATYPE_DOUBLE , Double.valueOf(0.0));
			
		}else if(BaseDataMeta.DataType.DATATYPE_FLOAT == first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_FLOAT == second.getDataType()){
			return new Constant(BaseDataMeta.DataType.DATATYPE_FLOAT , Float.valueOf(0.0f));
			
		}else if(BaseDataMeta.DataType.DATATYPE_LONG == first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_LONG == second.getDataType()){
			return new Constant(BaseDataMeta.DataType.DATATYPE_LONG , Long.valueOf(0l));
			
		}else {
			return new Constant(BaseDataMeta.DataType.DATATYPE_INT , Integer.valueOf(0));
		}
	}
	
}