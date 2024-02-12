# Fancy String Builder

**Not StringBuilder you want, but StringBuilder I deserve**

Opinionated StringBuilder wrapper.

It was designed for brevity, readability and convenience.

Example:

```java
var fsb = new FancyStringBuilder();

fsb.l("package %s;", targetPackage);
fsb.n();
fsb.l("import lombok.Data;");
fsb.n();
fsb.l("/**").l(spec.getDescription()).l("*/");
fsb.l("@Data");
fsb.l("public class %s ", spec.getName()).a("{").bb();
for (var field : spec.getFields()) {
    fsb
        .l("/**")
        .l(field.getDescription()).l("*/")
        .l("private final %s %s;", field.getType(), field.getName())
        .n();
}
fsb.eb().l("}");

return fsb.toString();
```

For more details refer to source code.


## Installation

It's single 100 LOC class - just copy-paste it to your project.

```bash
curl https://raw.githubusercontent.com/butvinm/fsb/master/src/main/java/butvinm/fsb/FancyStringBuilder.java -O
```
