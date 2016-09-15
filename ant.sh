#!/usr/bin/bash

# -----------------------------------------------------------------------------
# Check if were running cygwin
# -----------------------------------------------------------------------------
case "`uname`" in
  CYGWIN*) cygwin=true ;;
  *) cygwin=false ;;
esac

# -----------------------------------------------------------------------------
# Initialize environment
# -----------------------------------------------------------------------------
if [ -f ~/.ENV ] && ! $cygwin ; then
    . ~/.ENV
fi


# -----------------------------------------------------------------------------
# Initialize required environment variables specifically for this project
# -----------------------------------------------------------------------------
export ANT_HOME=../commonbuild/ant
export JAVA_HOME=$JAVA170_HOME

# -----------------------------------------------------------------------------
# Validate the required and optional environment variables.
# -----------------------------------------------------------------------------
ENV_ERROR=false

if [ -z "$ANT_HOME" ] || [ ! -d $ANT_HOME ] ; then
    echo "Warning: Your ANT_HOME(\"$ANT_HOME\") environment variable has not"
    echo "         been initialized or the directory it references does not exist."
    ENV_ERROR=true
fi
if [ -z "$JAVA_HOME" ] || [ ! -d $JAVA_HOME ] ; then
    echo "Warning: Your JAVA_HOME(\"$JAVA_HOME\") environment variable has not"
    echo "         been initialized or the directory it references does not exist."
    ENV_ERROR=true
fi

# -----------------------------------------------------------------------------
# Invoke Ant if no errors
# -----------------------------------------------------------------------------
if $ENV_ERROR ; then
    echo " "
    echo "         Review the readme.txt file associated with this project for "
    echo "         specific instructions on configuring your build environment."    
else
    $ANT_HOME"/bin/ant" "$@" -buildfile build.xml -Dbasedir=.
fi
