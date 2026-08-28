package cc.hongz.ztools.setting.yaml;

import cc.hongz.ztools.core.io.IoUtil;
import cc.hongz.ztools.core.io.resource.ResourceUtil;
import cc.hongz.ztools.core.lang.Assert;
import cc.hongz.ztools.core.lang.Dict;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * 基于Snakeyaml的的YAML读写工具。
 *
 * @author looly
 * @since 5.7.14
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class YamlUtil {

    /**
     * 从classpath或绝对路径加载YAML文件。
     *
     * @param path YAML路径，相对路径相对classpath
     * @return 加载的内容，默认Map
     */
    public static Dict loadByPath(final String path) {
        return loadByPath(path, Dict.class);
    }

    /**
     * 从classpath或绝对路径加载YAML文件。
     *
     * @param <T>  Bean类型，默认map
     * @param path YAML路径，相对路径相对classpath
     * @param type 加载的Bean类型，即转换为的bean
     * @return 加载的内容，默认Map
     */
    public static <T> T loadByPath(final String path, final Class<T> type) {
        return load(ResourceUtil.getStream(path), type);
    }

    /**
     * 从流中加载YAML。
     *
     * @param <T>  Bean类型，默认map
     * @param in   流
     * @param type 加载的Bean类型，即转换为的bean
     * @return 加载的内容，默认Map
     */
    public static <T> T load(final InputStream in, final Class<T> type) {
        return load(IoUtil.getBomReader(in), type);
    }

    /**
     * 加载YAML，加载完毕后关闭{@link Reader}。
     *
     * @param reader {@link Reader}
     * @return 加载的Map
     */
    public static Dict load(final Reader reader) {
        return load(reader, Dict.class);
    }

    /**
     * 加载YAML，加载完毕后关闭{@link Reader}。
     *
     * @param <T>    Bean类型，默认map
     * @param reader {@link Reader}
     * @param type   加载的Bean类型，即转换为的bean
     * @return 加载的内容，默认Map
     */
    public static <T> T load(final Reader reader, final Class<T> type) {
        return load(reader, type, true);
    }

    /**
     * 加载YAML。
     *
     * @param <T>           Bean类型，默认map
     * @param reader        {@link Reader}
     * @param type          加载的Bean类型，即转换为的bean
     * @param isCloseReader 加载完毕后是否关闭{@link Reader}
     * @return 加载的内容，默认Map
     */
    public static <T> T load(final Reader reader, final Class<T> type, final boolean isCloseReader) {
        Assert.notNull(reader, "Reader must be not null !");
        //noinspection unchecked
        final Class<T> resolvedType = null == type ? (Class<T>) Object.class : type;

        final Yaml yaml = new Yaml();
        try {
            return yaml.loadAs(reader, resolvedType);
        } finally {
            if (isCloseReader) {
                IoUtil.close(reader);
            }
        }
    }

    /**
     * 将Bean对象或者Map写出到{@link Writer}。
     *
     * @param object 对象
     * @param writer {@link Writer}
     */
    public static void dump(final Object object, final Writer writer) {
        final DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        dump(object, writer, options);
    }

    /**
     * 将Bean对象或者Map写出到{@link Writer}。
     *
     * @param object        对象
     * @param writer        {@link Writer}
     * @param dumperOptions 输出风格
     */
    public static void dump(final Object object, final Writer writer, final DumperOptions dumperOptions) {
        final DumperOptions resolvedDumperOptions = null == dumperOptions ? new DumperOptions() : dumperOptions;
        final Yaml yaml = new Yaml(resolvedDumperOptions);
        yaml.dump(object, writer);
    }
}
