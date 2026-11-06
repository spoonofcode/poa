# Agent Usage Guide

This guide explains how to effectively use the custom agents defined in this repository.

## Available Agents

| Agent | Purpose | Key Use Cases |
|-------|---------|---------------|
| `@test-agent` | Write comprehensive tests | Unit tests, integration tests, test coverage |
| `@docs-agent` | Create technical documentation | API docs, ADRs, tutorials |
| `@security-agent` | Security analysis | Vulnerability scanning, secure coding |
| `@architect-agent` | Architecture decisions | Design patterns, system architecture |
| `@dev-agent` | General development | Feature implementation, bug fixes |
| `@devops-agent` | CI/CD and deployment | Build scripts, Jenkins configuration |

## How to Use Agents

### In GitHub Copilot Chat

Simply mention the agent by name:

```
@test-agent write unit tests for UserRepository
@docs-agent document the authentication flow
@security-agent review this code for vulnerabilities
```

### Best Practices

1. **Be specific with your request:**
- ✅ Good: "@test-agent write unit tests for fetchUserById with edge cases"
- ❌ Bad: "@test-agent help with tests"

2. **Provide context:**
- Show the agent the relevant code
- Mention specific files or components
- Reference existing patterns to follow

3. **Use the right agent for the job:**
- Don't ask @docs-agent to write code
- Don't ask @test-agent to modify source code
- Each agent has specific boundaries

## Examples

### Writing Tests
```
@test-agent I need comprehensive tests for this UserRepository class.
It should test:
- Successful user fetch
- Error handling for blank IDs
- Network failure scenarios
- Caching behavior

[paste UserRepository code]
```

### Creating Documentation
```
@docs-agent Create documentation for the authentication module.
Include:
- Overview of the auth flow
- Code examples for common use cases
- Error handling patterns
- Links to related modules

Location: docs/authentication.md
```

### Security Review
```
@security-agent Review this login implementation for security issues.
Focus on:
- Credential storage
- Token management
- Network security
- Input validation

[paste LoginViewModel code]
```

## Agent Capabilities

### What Agents Can Do

- ✅ Read your existing codebase
- ✅ Write to designated directories (tests, docs)
- ✅ Run configured commands (build, test, lint)
- ✅ Suggest improvements with examples
- ✅ Follow project conventions automatically

### What Agents Cannot Do

- ❌ Commit code directly
- ❌ Modify files outside their boundaries
- ❌ Access external resources without permission
- ❌ Override security restrictions
- ❌ Disable tests or checks

## Agent Boundaries

Each agent has clear boundaries defined in three tiers:

### ✅ Always Do
Actions that are safe and expected:
- @test-agent writes to `test/` directories
- @docs-agent writes to `docs/` directory
- @security-agent reports vulnerabilities

### ⚠️ Ask First
Actions that need confirmation:
- Adding new dependencies
- Modifying shared configurations
- Making architectural changes

### 🚫 Never Do
Actions that are forbidden:
- Committing secrets
- Removing failing tests
- Modifying production code (for non-dev agents)

## Tips for Better Results

1. **Iterate:** Start with a simple request, then refine
2. **Provide examples:** Show the agent what good output looks like
3. **Be explicit:** Don't assume the agent knows your preferences
4. **Review output:** Always review and validate agent suggestions
5. **Give feedback:** Mention what worked and what didn't

## Common Workflows

### Adding a New Feature
1. Use `@dev-agent` to implement the feature
2. Use `@test-agent` to write comprehensive tests
3. Use `@docs-agent` to document the feature
4. Use `@security-agent` to review for vulnerabilities

### Fixing a Bug
1. Use `@test-agent` to write a failing test that reproduces the bug
2. Use `@dev-agent` to fix the bug
3. Verify the test now passes
4. Use `@docs-agent` to update relevant documentation

### Refactoring
1. Use `@architect-agent` to review the refactoring plan
2. Use `@dev-agent` to implement changes
3. Use `@test-agent` to ensure tests still pass
4. Use `@docs-agent` to update architecture documentation

## Troubleshooting

### Agent not following instructions?
- Check that you're using the correct agent for the task
- Be more specific in your request
- Provide concrete examples of what you want

### Agent trying to modify forbidden files?
- The agent should self-correct based on boundaries
- Remind the agent of its boundaries explicitly
- Review the agent's `.md` file for accuracy

### Need to customize an agent?
- Edit the agent's `.md` file in `.github/agents/`
- Add more specific examples
- Refine the boundaries section
- Test the changes incrementally

## Learn More

- [AGENTS.md](../AGENTS.md) - Main agent instructions
- [AGENTS_IMPROVEMENTS.md](./AGENTS_IMPROVEMENTS.md) - Best practices applied
- [GitHub Blog: Writing Great Agents](https://github.blog/ai-and-ml/github-copilot/how-to-write-a-great-agents-md-lessons-from-over-2500-repositories/)