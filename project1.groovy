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
def project = 'mlbit/cicd-dsl-test'
def branchApi = new URL("https://api.github.com/repos/${project}/branches")
def branches = new groovy.json.JsonSlurper().parse(branchApi.newReader())
branches.each {
    def branchName = it.name
    def jobName = "${project}-${branchName}".replaceAll('/','-')
    job(jobName) {
        scm {
            git("git://github.com/${project}.git", branchName)
        }
        steps {
            maven("test -Dproject.name=${project}/${branchName}")
        }
    }
}
def foldername = "playground"
folder(foldername) {
    displayName("displayname")
    description("Description")
}
