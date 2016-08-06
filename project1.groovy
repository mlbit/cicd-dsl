job('DSL-Tutorial-1-Test') {
    scm {
        git('git://github.com/mlbit/cicd-dsl-test.git')
    }
    triggers {
        scm('H/15 * * * *')
    }
    steps {
        maven('-e clean test')
    }
}
