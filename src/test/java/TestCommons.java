import com.everingthing.commons.Nullable;

/**
 * 接口
 *
 * @author baofei
 * @date 2017/12/11
 */
public class TestCommons {

	@Nullable
	private String paramsNullable;

	private String paramsNotable;

	public static void main(String[] args) {
		TestCommons commons = new TestCommons();
		commons.genResult(null);
		commons.genResult(commons.paramsNotable);
		commons.genResult(null);
	}

	@Nullable
	private String genResult(@Nullable String param) {
		return param.replace("", "_");
	}
}
