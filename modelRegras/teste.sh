echo "== RUNNERS (arquivo, pacote declarado e anotacoes) =="
for f in $(grep -rl "@RunWith(CucumberSBTestes" src); do
  echo "--- $f"
  grep -m1 "^package" "$f"
  grep -A4 "@RunWith" "$f"
done

echo; echo "== FEATURES (local e primeiras linhas) =="
for f in $(find src -name "*.feature"); do
  echo "--- $f"
  head -3 "$f" | cat -A
done

echo; echo "== DEPENDENCIAS JUNIT E CUCUMBER =="
mvn -q dependency:tree -Dincludes=junit,info.cukes -DoutputFile=/dev/stdout

echo; echo "== CONFIG DO SUREFIRE NO POM =="
grep -n -A15 "maven-surefire-plugin" pom.xml

