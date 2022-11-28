# Main Class, change as preferred
MAIN_CLASS := App
TARGET_EXEC := $(MAIN_CLASS).jar

# Directories for where to find build files, libraries and sources, respectively
BUILD_DIR := ./bin
LIB_DIR := ./lib
SRC_DIR := ./src
DOC_DIR := ./doc

# Classes are generated in a subdirectory
CLASS_DIR := $(BUILD_DIR)/class

# Classpath for build time and run time
BUILD_CP = "$(SRC_DIR)/:$(LIB_DIR)/*"
RUN_CP = "$(CLASS_DIR)/:$(LIB_DIR)/*"

# List of source files
SRCS := $(shell find $(SRC_DIR) -name '*.java' -print)

# List of class files
CLS := $(SRCS:$(SRC_DIR)/%.java=$(CLASS_DIR)/%.class)

# Compile and run project
run: classes
	java -cp $(RUN_CP) $(MAIN_CLASS)

# Compile sources to classes
classes: $(SRCS)
	javac -d $(CLASS_DIR) -cp $(BUILD_CP) $(SRCS)

# Generate .jar artifact
jar: classes
	jar --create --file $(BUILD_DIR)/$(TARGET_EXEC) --main-class $(MAIN_CLASS).main $(CLS)

# Generate documentation
docs: $(SRCS)
	javadoc -d $(DOC_DIR) -cp $(BUILD_CP) $(SRCS)

# Clean BUILD_DIR by deleting it
clean:
	rm -r $(BUILD_DIR)

# Delete documentation
cleandoc:
	rm -r $(DOC_DIR)

all: classes jar docs

cleanall: clean cleandoc

.PHONY: classes run jar clean docs cleandoc
